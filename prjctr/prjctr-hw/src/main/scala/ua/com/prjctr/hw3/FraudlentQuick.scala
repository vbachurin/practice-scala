package ua.com.prjctr.hw3

import java.io._

import scala.collection.mutable


object FraudlentQuick {

  def activityNotifications(expenditure: Array[Int], d: Int): Int = {
    var i = d
    var counter = 0
    while (i < expenditure.length) {
      if (expenditure(i) >= findMedian(expenditure, d, i) * 2)
        counter += 1
      i += 1
    }
    counter
  }

  def findMedian(expenditure: Array[Int], d: Int, i: Int): Double = {
    val start = i - d
    val end = i - 1
    if (d % 2 == 0) {
      val mean = (
        select(expenditure, start, end, (start + end) / 2) +
          select(expenditure, start, end, (start + end) / 2 + 1)
        ).toDouble / 2
      mean
    }
    else
      select(expenditure, start, end, (start + end) / 2)
  }

  private def select(arr: Array[Int], start: Int, end: Int, k: Int): Int = {
    if (k < 0 || k >= arr.length) throw new IllegalArgumentException("index is not between 0 and " + arr.length + ": " + k)
    val a = mutable.ArraySeq(arr: _*)
    //    val a = mutable.ArraySeq(arr.slice(start, end): _*)
    //    util.Random.shuffle(a)
    var lo = start
    var hi = end
    while (hi > lo) {
      val i = partition(a, lo, hi)
      if (i > k) hi = i - 1
      else if (i < k) lo = i + 1
      else return a(i)
    }
    a(lo)
  }

  private def partition(arr: mutable.ArraySeq[Int], low: Int, high: Int) = {
    val pivot = arr(high)
    var i = low - 1
    // index of smaller element
    var j = low
    while (j < high) { // If current element is smaller than or equal to pivot
      if (arr(j) <= pivot) {
        i += 1
        // swap arr[i] and arr[j]
        val temp = arr(i)
        arr(i) = arr(j)
        arr(j) = temp
      }
      j += 1;
    }
    // swap arr[i+1] and arr[high] (or pivot)
    val temp = arr(i + 1)
    arr(i + 1) = arr(high)
    arr(high) = temp
    i + 1
  }

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val nd = stdin.readLine.split(" ")

    val n = nd(0).trim.toInt

    val d = nd(1).trim.toInt

    val expenditure = stdin.readLine.split(" ").map(_.trim.toInt)
    val result = activityNotifications(expenditure, d)

    printWriter.println(result)

    printWriter.close()
  }

}
