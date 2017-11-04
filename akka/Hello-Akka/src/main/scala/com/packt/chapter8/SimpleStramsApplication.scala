package com.packt.chapter8

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}

object SimpleStramsApplication extends App {
  implicit val actorSystem = ActorSystem("SimpleStream")
  implicit val actorMaterializer = ActorMaterializer()


  val fileList = List(
    "src/main/resources/testfile1.txt",
    "src/main/resources/testfile2.txt",
    "src/main/resources/testfile3.txt"
  )

  val stream = Source(fileList)
    .map(new java.io.File(_))
    .filter(_.exists())
    .filter(_.length() != 0)
    .to(Sink.foreach(f => println(s"Absolute path: ${f.getAbsolutePath}")))
  stream.run
}
