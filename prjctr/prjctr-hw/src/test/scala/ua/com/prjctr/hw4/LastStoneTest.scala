package ua.com.prjctr.hw4

import org.scalatest.FunSuite
import org.scalatest.Matchers._
import org.scalatest.prop.TableDrivenPropertyChecks._

class FirstPlayersWinsTest extends FunSuite {

  val maxK, maxTi = 1000000000
  val maxN = 500

  val trips =
    Table(
      ("m", "n", "t", "wins"),
      (1, 5, Array(1, 3, 4), 4),
    )


  forAll(trips) { (m: Int, n: Int, t: Array[Int], wins: Int) =>
    LastStone.firstPlayersWins(m, n, t) shouldBe wins
  }

}

/*
k = 6
1 3 4

4(2)
x

if stones left <= 2*max(Ti) => pick move to leave Sally with 'hole' stones
 */
class PlayRoundTest extends FunSuite {

  val trips =
    Table(
      ("k", "t", "wins"),
      (1, Array(1, 3, 4), true),
      (2, Array(1, 3, 4), false),
      (3, Array(1, 3, 4), true),
      (5, Array(1, 3, 4), true),
    )


  forAll(trips) { (k: Int, t: Array[Int], win: Boolean) =>
    LastStone.playRound(k, t) shouldBe win
  }

}
