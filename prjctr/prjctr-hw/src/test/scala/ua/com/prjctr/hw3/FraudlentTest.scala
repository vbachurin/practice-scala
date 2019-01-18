package ua.com.prjctr.hw3

import org.scalatest.FunSuite
import org.scalatest.Matchers._
import org.scalatest.prop.TableDrivenPropertyChecks._
import ua.com.prjctr.hw3.Fraudlent._

import scala.collection.mutable
import scala.util.Random



class FraudlentMedianTest extends FunSuite {

  val medianInputs =
//    Table(
//      ("expenditure", "d", "i", "notifications"),
//      (Array(2, 3, 4, 2, 3, 1, 8, 4, 5), 5, 5, 3.0),
//      (Array(2, 3, 4, 2, 3, 6, 8, 4, 5), 5, 7, 4.0),
//      (Array(1, 2, 3, 4, 0), 4, 4, 2.5),
//      (Array(10, 20, 30, 40, 50), 3, 3, 20.0),
//    )
      Table(
        ("expenditures", "median"),
        (Array(2, 3, 4, 2, 3), 3.0),
        (Array(3, 4, 2, 3, 6), 3.0),
        (Array(4, 2, 3, 6, 8), 4.0),
        (Array(2, 3, 6, 8, 4), 4.0),
        (Array(3, 6, 8, 4, 5), 5.0),
        (Array(1, 2, 3), 2.0),
        (Array(2, 3, 4), 3.0),
        (Array(3, 4, 4), 4.0),
        (Array(10, 20, 30), 20.0),
        (Array(20, 30, 40), 30.0),
        (Array(30, 40, 50), 40.0),
      )

//  forAll(medianInputs) { (expenditure: Array[Int], d: Int, i: Int, median: Double) =>
  forAll(medianInputs) { (expenditure: Array[Int], median: Double) =>
    val fraudlent = new Fraudlent(Array.empty)
//    fraudlent.findMedian(d, i) shouldBe median
    val pq = mutable.PriorityQueue[Int]()
    pq.enqueue(expenditure: _*)
    fraudlent.findMedian(pq) shouldBe median
  }
}

class FraudlentNotificationsTest extends FunSuite {


  val inputs =
    Table(
      ("expenditure", "d", "notifications"),
      (Array(2, 3, 4, 2, 3, 6, 8, 4, 5), 5, 2),
      (Array(1, 2, 3, 4, 4), 4, 0),
      (Array(10, 20, 30, 40, 50), 3, 1),
    )


    forAll(inputs) { (expenditure: Array[Int], d: Int, notifications: Int) =>
      val fraudlent = new Fraudlent(expenditure)
      fraudlent.activityNotifications(d) shouldBe notifications
    }
}

class FraudlentPerformanceTest extends FunSuite {
  val maxN = 200000
  test("performance") {
    val expenditure = Array.iterate(0, maxN)(_ => Random.nextInt(200))
    val start = System.currentTimeMillis()
    val fraudlent = new Fraudlent(expenditure)
    val result = fraudlent.activityNotifications(1000)
    val end = System.currentTimeMillis()
    println(end - start)
    result shouldBe 1
  }
}
