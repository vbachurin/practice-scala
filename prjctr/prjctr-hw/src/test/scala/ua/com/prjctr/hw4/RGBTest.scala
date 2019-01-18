package ua.com.prjctr.hw4

import org.scalatest.FunSuite
import org.scalatest.Matchers._
import org.scalatest.prop.TableDrivenPropertyChecks._

class RGBTest extends FunSuite {

  val rounds =
    Table(
      ("houses", "expected"),
      (IndexedSeq(
        71 :: 39 :: 44 :: Nil,
        32 :: 83 :: 55 :: Nil,
        51 :: 37 :: 63 :: Nil,
        89 :: 29 :: 100 :: Nil,
        83 :: 58 :: 11 :: Nil,
        65 :: 13 :: 15 :: Nil,
        47 :: 25 :: 29 :: Nil,
        60 :: 66 :: 19 :: Nil
      ), 253),

    )


  forAll(rounds) { (houses: IndexedSeq[List[Int]], expected: Int) =>
//    RGB.calculateResult(houses) shouldBe expected
  }

}
