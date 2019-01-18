package ua.com.prjctr.hw3

import scala.io.StdIn

object Carpathians {


  def main(args: Array[String]): Unit = {
    val pattern = raw"(\d) (\d)".r
    val pattern(n, k) = StdIn.readLine()
    val t = StdIn.readLine().split(" ").map(_.toInt)

    val d = longestDailyHike(k.toInt, t, n.toInt)
    println(d)
  }

  def longestDailyHike(k: Int, t: Array[Int], n: Int): Int = {
    var l = t(n - 1) - t(n - 2) - 1// <last element> - <second to last element> - 1
    var r = t(n - 1)

    while (r - l > 1) {
      val mid = (l + r) / 2
      if (possible(k, t, n, mid))
        r = mid
      else
        l = mid
    }
    r
  }

  def possible(k: Int, t: Array[Int], n: Int, rangeToday: Int): Boolean = {
    var days = 0
    var stopoverIndex = 0
    while (t(stopoverIndex) < t(n - 1) && days <= k + 1) {
      stopoverIndex = furthestHikeToday(stopoverIndex, t, t(stopoverIndex) + rangeToday)
      days += 1
    }
    days <= k + 1 // if k stops, then k + 1 days of hiking available

  }

  /* Finding the furthest point (element) group can get to with given velocity */
  def furthestHikeToday(l0: Int, t: Array[Int], maxRangeToday: Int) = {
    var l = l0
    var r = t.length - 1

    while (r - l > 1) {
      val mid = (l + r) / 2
      if (t(mid) < maxRangeToday)
        l = mid
      else
        r = mid
    }
    if (t(r) <= maxRangeToday)
      r
    else
      l
  }

}
