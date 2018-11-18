package com.octopus

import java.io.File
import com.softwaremill.sttp._
import org.scalatest.{FlatSpec, Matchers}

class SendRequestTest extends FlatSpec with Matchers {

  "sendReqest" should "work" in {
    val oc: OctopusArgValues = OctopusArgValues(
      ingredients = new File("ingredients-one.txt"),
      meals = new File("output"),
      numberOfLimbs = 1
    )

    val testInstance = new Grabber(oc)
    val fileName = "test.bin"
    testInstance.sendRequest(new Food(uri"http://www.ovh.net/files/1Mio.dat", fileName))
    println("FILES:")
    new File(".").listFiles().foreach(println)
  }
}
