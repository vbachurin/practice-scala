package ua.com.prjctr.binsearch

import org.scalatest.FunSuite
import org.scalatest.Matchers._

class GarlandTest extends FunSuite {

  test("testFindB") {
    Garland.findB(8, 15) shouldBe 9.75
  }

}
