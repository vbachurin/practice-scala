package ua.com.prjctr.hw3

import scala.io.StdIn

object CarpathiansBackup2 {


  def main(args: Array[String]): Unit = {
    val pattern = raw"(\d) (\d)".r
    val pattern(n, k) = StdIn.readLine()
    val t = StdIn.readLine().split(" ").map(_.toInt)

    println(s"$n $k")
    println(s"$t")
    val d: Int = minDuration(k.toInt, t, n.toInt)
  }

  def minDuration(k0: Int, t: Array[Int], n: Int): Int = {
    var p = t(n - 1) / (k0 + 1)
    println(s"Proportional value is $p")
    var k = k0
    var l = 1
    var r = n
    var closestGreaterIndex = 0
    var res = 0 //t(n - 1)
    var curr = 0
    while (k > 0) {
//    while (t(closestGreaterIndex) + p < t(n - 1) && k > 0) {
      l = closestGreaterIndex
      closestGreaterIndex = binSearch(l, r, t, t(closestGreaterIndex) + p)
      curr = t(closestGreaterIndex) - t(l)

      println(s"Current: $curr, current closestGreaterIndex: $closestGreaterIndex, t(closestGreaterIndex): ${t(closestGreaterIndex)}, k: $k")
      k -= 1
    }
    p = t(n - 1) - t(binSearch(l, r, t, (closestGreaterIndex) + p))
    res = Math.max(p, curr)
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
