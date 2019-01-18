package ua.com.prjctr.hw3

import scala.io.StdIn

object CarpathiansBackup {


  def main(args: Array[String]): Unit = {
    val pattern = raw"(\d) (\d)".r
    val pattern(n, k) = StdIn.readLine()
    val t = StdIn.readLine().split(" ").map(_.toInt)

    println(s"$n $k")
    println(s"$t")
    val d: Int = minDuration(k.toInt, t, n.toInt)
  }

  def minDuration(k: Int, t: Array[Int], n: Int): Int = {
    val p = t(n - 1) / (k + 1)
    println(s"Proportional value is $p")
    var l = 0
    var r = n
    var closestGreaterIndex = 0
    var res = p
    while (t(closestGreaterIndex) + p < t(n - 1)) {
      l = closestGreaterIndex
      closestGreaterIndex = binSearch(l, r, t, closestGreaterIndex + p)
//      if (t(closestGreaterIndex) - t(l) > p)
//        closestGreaterIndex -= 1
      res = Math.max(p, t(closestGreaterIndex) - t(l))
    }
    res
  }

  def binSearch(l0: Int, r0: Int, t: Array[Int], p: Int) = {
    var l = l0
    var r = r0
    var mid = (l + r) / 2

    while (r - l > 1) {
      mid = (l + r) / 2
      if (t(mid) < p)
        l = mid
      else
        r = mid

    }
    println(s"Closest greater for $p is ${t(r)}")
    r
  }
}
