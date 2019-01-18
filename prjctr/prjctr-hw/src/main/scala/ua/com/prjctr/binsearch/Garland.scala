package ua.com.prjctr.binsearch

import scala.io.StdIn

object Garland {


  def main(args: Array[String]): Unit = {
    val pattern = raw"(\d) (\d)".r
    val pattern(n, a) = StdIn.readLine()

    val b: Double = findB(n.toInt, a.toDouble)
  }

  def possible(n: Int, a: Double, b: Double): Boolean = {
    var h = a
    var i = 2
    while(h > 0.0 && i <= n) {
      h = (a + h*(i - 1))/i - i - 1
      println(s"h: $h, i: $i")
      i += 1
    }
    true
  }

  /*
  H[i] = (H[1] + H[i+11]*(i - 1))/i - (i - 1)
   */

  def findB(n: Int, a: Double): Double = {
    var l = 0.0
    var r = 1000.0
    var mid = (r + l) / 2
    while (r - l > 0.005) {
      println(s"l: $l, r: $r, mid: $mid")//, arr[mid]: ${arr(mid)}")
      mid = (r + l) / 2
      if (possible(n, a, mid))
        r = mid
      else
        l = mid
    }
    return l
  }
}
