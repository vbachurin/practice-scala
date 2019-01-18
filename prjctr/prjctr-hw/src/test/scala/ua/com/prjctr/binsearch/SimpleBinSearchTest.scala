package ua.com.prjctr.binsearch

import org.scalatest.FunSuite
import org.scalatest.Matchers._

class SimpleBinSearchTest extends FunSuite {

  test("testBinarySearch") {
    val arr = Array(1, 4, 6, 7, 9, 15, 18, 192)
    SimpleBinSearch.binarySearch(arr, 6) shouldBe 2

  }

}
