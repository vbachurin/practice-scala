package ua.com.prjctr.hw5

import org.scalatest.FunSuite
import org.scalatest.Matchers._
import org.scalatest.prop.TableDrivenPropertyChecks._

class OptimalTrapPlacementTestFunSuite extends FunSuite {

  val rounds =
    Table(
      ("positions", "expected"),
      (Array(1, 1, 1), 2),
      (Array(1, 2, 1, 1), 3),
      (Array(4, 1, 2, 7, 5, 3, 1), 14),
      (Array(3, 10, 9, 1, 6), 18),
    )


  forAll(rounds) { (a: Array[Int], expected: Int) =>
    OptimalTrapPlacement.trapCorridor(a, a.size) shouldBe expected
  }

}
