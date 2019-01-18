package ua.com.prjctr.hw3

import org.scalatest.FunSuite
import org.scalatest.Matchers._
import ua.com.prjctr.hw3.Lily._

import scala.collection.mutable

class LilyTest2 extends FunSuite {


  test("buildHeapAsc") {
    buildHeap(mutable.ArraySeq(7, 15, 12, 3).zipWithIndex, false) shouldBe mutable.ArraySeq((3 -> 3), (7 -> 0), (12 -> 2), (15 -> 1))
  }
//
//  test("buildHeapDesc") {
//    buildHeap(mutable.ArraySeq(50, 40, 30, 10, 20), true) shouldBe mutable.ArraySeq(50, 30, 40, 10, 20)
//  }

  test("buildHeapDesc") {
    buildHeap(mutable.ArraySeq(3, 4, 2, 5, 1).zipWithIndex, true) shouldBe mutable.ArraySeq((5 -> 3), (4 -> 1), (2 -> 2), (3 -> 0), (1 -> 4))
  }
}
