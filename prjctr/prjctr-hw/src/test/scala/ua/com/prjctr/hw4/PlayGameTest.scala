package ua.com.prjctr.hw4

import org.scalatest.FunSuite
import org.scalatest.Matchers._
import org.scalatest.prop.TableDrivenPropertyChecks._

class PlayGameTest extends FunSuite {

  val maxK, maxTi = 1000000000
  val maxN = 500

  val rounds =
    Table(
      ("you", "computer", "expected"),
      (Array(5, 15, 100, 1, 5), Array(5, 15, 100, 1, 5), 120),
      (Array(1, 3, 5, 7, 9, 11, 13, 15, 17, 19), Array(2, 4, 6, 8, 10, 12, 14, 16, 18, 20), 99),
      (Array(651, 321, 106, 503, 227, 290, 915, 549, 660, 115, 491, 378, 495, 789, 507, 381, 685, 530, 603, 394, 7, 704, 101, 620, 859, 490, 744, 495, 379, 781, 550, 356, 950, 628, 177, 373, 132, 740, 946, 609, 29, 329, 57, 636, 132, 843, 860, 594, 718, 849),
        Array(16, 127, 704, 614, 218, 67, 169, 621, 340, 319, 366, 658, 798, 803, 524, 608, 794, 896, 145, 627, 401, 253, 137, 851, 67, 426, 571, 302, 546, 225, 311, 111, 804, 135, 284, 784, 890, 786, 740, 612, 360, 852, 228, 859, 229, 249, 540, 979, 55, 82),
        25084),
      (Array(1000, 1000, 1000), Array(1000, 1000, 1000), 0)
    )


  forAll(rounds) { (you: Array[Int], computer: Array[Int], expected: Int) =>
    PlayGame.lineUpAndCountSurvivors(you, computer) shouldBe expected
  }

}
