package ua.com.prjctr.hw3

import org.scalatest.FunSuite
import org.scalatest.Matchers._
import org.scalatest.prop.TableDrivenPropertyChecks._
import ua.com.prjctr.hw3.Difference._

import scala.util.Random

class DifferenceTest extends FunSuite {

  val maxN = 200000

  val inputs =
    Table(
      ("n", "a", "d"),
      (10, Array(-20, -3916237, -357920, -3620601, 7374819, -7330761, 30, 6246457, -6461594, 266854), List((-20, 30))),
      (12, Array(-20, -3916237, -357920, -3620601, 7374819, -7330761, 30, 6246457, -6461594, 266854, -520, -470), List((-520, -470), (-20, 30))),
      (4, Array(5, 4, 3, 2), List((2, 3), (3, 4), (4, 5))),
//      (maxN, Array.fill(maxN)(Random.nextInt), List()),

    )


  forAll(inputs) { (n: Int, a: Array[Int], d: List[(Int, Int)]) =>
    closestNumbers(a) shouldBe d
  }

}
