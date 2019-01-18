package ua.com.prjctr.hw5

import org.scalatest.FunSuite
import org.scalatest.prop.TableDrivenPropertyChecks.forAll
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.Matchers._

class MaxMoneyTest extends FunSuite {


  val rounds =
    Table(
      ("n", "k", "cost", "expected"),
//      (4, 1, Array(1, 1, 1, 1, 1), 1),
      (4, 2, Array(10, 120, 0, 0, 50), 1),
//      (4, 1, Array(1, 2, 3, 4, 5), 14),
    )


  forAll(rounds) { (n: Int, k: Int, a: Array[Int], expected: Int) =>
    MaxMoney.maxMoney(n, k, a) shouldBe expected
  }

}
