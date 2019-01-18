package ua.com.prjctr.hw3

import org.scalatest.FunSuite
import org.scalatest.Matchers._
import org.scalatest.prop.TableDrivenPropertyChecks._
import ua.com.prjctr.hw3.LilyQuick._

import scala.collection.mutable

class LilyTest extends FunSuite {

  val maxN = 200000

  val inputs =
    Table(
      ("a", "d"),
      (Array(7, 15, 12, 3), 2),
      (Array(3, 7, 12, 15), 0),
      (Array(2, 5, 3, 1), 2),
      (Array(3, 4, 2, 5, 1), 2), // reverse
      (Array(1000000000), 0),
      (Array(2, 5, 3, 1), 2), // reverse
      (Array(4, 7, 8, 10, 38, 18, 21, 46, 5), 5),
//      (Array(4, 7, 8, 10, 38, 5), 4),
      (Array(7, 8, 10, 38, 5), 2), // reverse
      (Array(2, 3, 5, 6, 14, 18, 20, 22, 29, 26, 23, 37, 42), 1),
      (Array(1, 19, 27, 6, 23, 17, 30, 31, 32, 38, 43), 2),
      (Array(5, 4, 3, 1, 2), 1),
      (Array(18, 33, 19, 17, 38, 28, 35, 49, 46, 31, 4, 26, 27, 12, 32), 13),

    )


  forAll(inputs) { (a: Array[Int], d: Int) =>
    lilysHomework(a) shouldBe d
  }


}
