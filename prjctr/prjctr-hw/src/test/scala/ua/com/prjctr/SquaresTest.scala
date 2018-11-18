package ua.com.prjctr

import org.scalatest.FunSuite
import org.scalatest.Matchers._

class SquaresTest extends FunSuite {

  test("testSquares") {
    Squares.squares(3, 9) shouldBe Seq(4, 9)
    Squares.res(3, 9) shouldBe 2
  }

  test("testSquares unlucky") {
    Squares.squares(17, 24) shouldBe Seq()
    Squares.res(17, 24) shouldBe 0
  }

}
