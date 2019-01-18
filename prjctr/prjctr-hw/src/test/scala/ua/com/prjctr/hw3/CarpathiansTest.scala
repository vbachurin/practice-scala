package ua.com.prjctr.hw3

import org.scalatest.FunSuite
import org.scalatest.Matchers._
import org.scalatest.prop.TableDrivenPropertyChecks._

import Carpathians._

class CarpathiansTest extends FunSuite {

  val maxK, maxTi = 1000000000
  val maxN = 500

  val trips =
    Table(
      ("k", "t", "d"),
      (1, Array(0, 4, 7, 11, 12), 7),
      (2, Array(0, 4, 7, 11, 12), 5),
      (3, Array(0, 4, 7, 11, 12), 4),
      (3, Array(0, 1, 3, 4, 7, 11, 12), 4),
      (1, Array(0, 1, 2), 1),
      (1, Array(0, 10, 20), 10),
      (1, Array(0, 1, 3), 2),
      (1, Array(0, 1, 10), 9),
      (1, Array(0, 100), 100),
      (10, Array(0, 100), 100),
      (0, Array(0, 0), 0),
      (maxK, Array(0, 0), 0),
      (maxK, Array(0, maxTi), maxTi),
      (maxK, Array(0, 10, 100, maxTi), maxTi - 100),
      (1, Array(0, maxTi), maxTi),
      (10, Array(0, maxTi), maxTi),
      (maxK - 2, Array.range(0, 1000, 2), 2),
      (maxK - 2, Array.fill(maxN)(0), 0),
//      (maxN - 2, Stream.range(0, maxTi + 1, maxTi/maxN).take(maxN + 1).toArray, maxTi/maxN),
      (maxN - 1, Array.range(start = 0, end = maxTi + 1, step = maxTi/maxN), maxTi/maxN),
    )


  forAll(trips) { (k: Int, t: Array[Int], d: Int) =>
    CarpathiansJava.longestDailyHike(k, t, t.length) shouldBe d
  }

}
