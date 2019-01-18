package ua.com.prjctr.binsearch

import org.scalatest.FunSuite
import org.scalatest.Matchers._

class RaskinBobbinsTest extends FunSuite {

  import RaskinBobbins._

  test("testRecursion") {
    val m = 4
    val c = Array(1, 4, 5, 3, 2).map(_.toString)
    val cZipped = c.map(m - _.toInt).zipWithIndex.sortBy(_._1).dropWhile(_._1 <= 0)

    println(cZipped.mkString(" "))
    recursion(c, cZipped, m) shouldBe (1, 4)
  }

  test("testRecursion2") {
    val m = 4
    val c = Array(2, 2, 4, 3).map(_.toString)
    val cZipped = c.map(m - _.toInt).zipWithIndex.sortBy(_._1).dropWhile(_._1 <= 0)

    println(cZipped.mkString(" "))
    recursion(c, cZipped, m) shouldBe (1, 2)
  }

  test("testRecursion3") {
    val m = 14
    val c = Array(5, 4, 8, 13, 6, 7).map(_.toString)
    val cZipped = c.map(m - _.toInt).zipWithIndex.sortBy(_._1).dropWhile(_._1 <= 0)

    println(cZipped.mkString(" "))
    recursion(c, cZipped, m) shouldBe (3, 5)
  }

}
