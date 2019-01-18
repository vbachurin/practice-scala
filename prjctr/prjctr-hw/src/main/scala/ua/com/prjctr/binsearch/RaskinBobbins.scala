package ua.com.prjctr.binsearch

import scala.io.StdIn
import scala.math._

object RaskinBobbins {

  def main(args: Array[String]): Unit = {

    val t = StdIn.readInt()
    val inputLists = for {
      _ <- 1 to t
      m = StdIn.readInt()
      n = StdIn.readInt()
      c = StdIn.readLine().split(" ")
    } {
//            println(c.mkString(" "))
      val cZipped = c.map(m - _.toInt).zipWithIndex.sortBy(_._1).dropWhile(_._1 <= 0)
//            println(cZipped.mkString(" "))
      val res = recursion(c, cZipped, m)
      println(res._1 + " " + res._2)
    }

  }

  def recursion(init: Array[String], reduced: Array[(Int, Int)], m: Int): (Int, Int) = {
    var i = 0
    var j = -1
    while (i < init.length && j == -1) {
      j = binarySearch(reduced, init(i).toInt)
      if (j == -1 || j == i) j = -1
      i += 1
    }
    (min(i, j + 1), max(i, j + 1))
  }

  def binarySearch(arr: Array[(Int, Int)], el: Int): Int = {
//        println(s"binSearch: arr is ${arr.mkString}, searching for value $el")
    var l = 0
    var r = arr.length
    var mid: Int = (r + l) / 2
    while (r - l > 1) {
//            println(s"l: $l, r: $r, mid: $mid, arr[mid]: ${arr(mid)}")
      mid = (r + l) / 2
      if (arr(mid)._1 > el)
        r = mid
      else
        l = mid
    }
//        println(s"binSearch: returning index $l, value is ${arr(l)}")
    return if (arr(l)._1 == el)
      arr(l)._2
    else
      -1
  }

}
