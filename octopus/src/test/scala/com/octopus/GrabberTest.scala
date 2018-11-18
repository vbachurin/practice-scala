package com.octopus

import java.io.File
import com.softwaremill.sttp._
import org.scalatest.{FlatSpec, Matchers}

class GrabberTest extends FlatSpec with Matchers {

  "toIngredient" should "return valid URL" in {
    implicit val limit: String = "1024k"
    val uri = "http://www.ovh.net/files/1Mio.dat"
    Grabber.toIngredient(s"$uri test.bin").uri should be (uri"$uri")

  }
}
