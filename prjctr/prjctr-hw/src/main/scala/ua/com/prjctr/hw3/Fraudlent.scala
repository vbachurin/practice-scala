package ua.com.prjctr.hw3

import java.io._

import scala.collection.mutable
import scala.util.Sorting


class Fraudlent(val expenditure: Array[Int]) {

  val min = 0
  val max = 200

  def activityNotifications(d: Int): Int = {
    var i = d
    var counter = 0
    while (i < expenditure.length) {
      val pq = new mutable.PriorityQueue[Int]()
      pq.enqueue(expenditure.slice(i - d, i): _*)
      if (expenditure(i) >= findMedian(pq) * 2)
        counter += 1
      i += 1
    }
    counter
  }

  def findMedian(pq: mutable.PriorityQueue[Int]) = {
    val n = pq.size
    var i = 0
    while (i < (n - 1) / 2) {
      pq.dequeue() // discard first half
      i += 1;
    }
    if (n % 2 != 0) { // odd length
      pq.dequeue().toDouble
    }
    else (pq.dequeue() + pq.dequeue()) / 2.0
  }

}

object Fraudlent {

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val nd = stdin.readLine.split(" ")

    val n = nd(0).trim.toInt

    val d = nd(1).trim.toInt

    val expenditure = stdin.readLine.split(" ").map(_.trim.toInt)
    val fraundlent = new Fraudlent(expenditure)
    val result = fraundlent.activityNotifications(d)

    printWriter.println(result)

    printWriter.close()
  }

}
