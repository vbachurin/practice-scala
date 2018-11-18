package com.octopus

import java.nio.file.Paths
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.io.{Source => IoSource}
import scala.util.{Failure, Success}
import akka.actor.ActorSystem
import akka.stream.scaladsl.{FileIO, Sink, Source}
import akka.stream.{ActorMaterializer, IOResult, ThrottleMode}
import akka.util.ByteString
import com.octopus.Grabber._
import com.softwaremill.sttp._
import com.softwaremill.sttp.akkahttp.AkkaHttpBackend
import com.typesafe.config.ConfigFactory

// Has got everything what is necesssary to download and store files
class Grabber(val args: OctopusArgValues) {

  private val inputFileSource = IoSource.fromFile(args.ingredients)

  implicit val actorSystem = ActorSystem("Grabber", ConfigFactory.parseString(
    s"""
      |akka.http.client.parsing.max-content-length = 1024m
      |akka.http.host-connection-pool.max-connections = ${args.numberOfLimbs}
    """.stripMargin))
  implicit val sttpBackend = AkkaHttpBackend.usingActorSystem(actorSystem)
  implicit val actorMaterializer = ActorMaterializer()
  implicit val ec = actorSystem.dispatcher

  // The wrapping method for the whole downloading process
  def prepareMeal(): Unit = {
    val start = System.currentTimeMillis()

    implicit val limit: String = args.carefulness // download limit to be used for Liquid entities

    val futureFileStreams: List[Future[IOResult]] =
      inputFileSource.getLines.toList // read input file lines into the List[Strings]
        .map(toIngredient) // convert to Ingredient's subclasses
        .map(sendRequest)  // download and store each ingredient file

    Future
      .sequence(futureFileStreams) // convert to Future[List[IOResult]]
      .onComplete {
        case Success(resultsList) =>
          val end = System.currentTimeMillis()
          println("Running time, seconds: " + (end - start) / 1000)
          println("Bytes downloaded: " + resultsList.map(_.count).sum)
          // tear down
          sttpBackend.close()
          actorSystem.terminate()
        case Failure(ex) =>
          ex.printStackTrace()
          sys.exit(1)
      }
  }

  def sendRequest(ingredient: Ingredient): Future[IOResult] = {

    val fileSink: Sink[ByteString, Future[IOResult]] = // akka-streams Sink for files to be stored
      FileIO.toPath(Paths.get(s"${args.meals}/${ingredient.output}"))

    val future = sttp
      .get(ingredient.uri) // send GET to the ingredient's URI
      .response(asStream[Source[ByteString, Any]]) // response as a stream of bytes (to apply bytes/sec limit)
      .mapResponse(r => throttleIfLiquid(r, ingredient)) // apply limit, if ingredient is eligible
      .mapResponse(_.runWith(fileSink)) // write stream to file
      .send()

    for { // extract response body, which is the file content
      response <- future
      body <- response.unsafeBody
    } yield body

  }

  // applies throttling if the ingredient is a liquid, no action otherwise
  def throttleIfLiquid(streamSource: Source[ByteString, Any],
                       ingredient: Ingredient) = {
    ingredient match {
      case l: Liquid =>
        streamSource.throttle(l.limit, 1 second, 1, _ => 1024, ThrottleMode.shaping)
      case tl: TestLiquid =>
        streamSource.throttle(tl.limit, 1 second, 1, _ => 1024, ThrottleMode.shaping)
      case _ => streamSource
    }
  }
}

object Grabber {
  val config = ConfigFactory.load
  val liquid = config.getString("grabber.liquid-marker") // configurable marker that identifies liquid
  val testLiquid = config.getString("grabber.test-liquid-marker")
  val testAddress = config.getString("grabber.test-address") // address containing real files for tests

  def toIngredient(line: String)(implicit limit: String): Ingredient = {
    val UriAndOutput = ("^(\\S+) (\\S+)$").r
    val UriAndOutput(uriString, output) = line // splitting URI and output file name using regex
    val uri = uri"$uriString"
    uri match {
      case l if l.path.contains(liquid)       => Liquid(uri, output)
      // e.g. in uri 'http://octopus-shop.com/water.jpg', the path 'water.jpg' contains 'water'
      case tl if tl.path.contains(testLiquid) => TestLiquid(uri, output)
      case tf if tf.host == testAddress       => TestFood(uri, output)
      case _                                  => Food(uri, output) // if not liquid, then it is food, no download limit needed
    }
  }

}
