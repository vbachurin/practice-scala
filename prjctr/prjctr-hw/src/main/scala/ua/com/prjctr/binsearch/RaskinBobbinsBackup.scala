package ua.com.prjctr.binsearch

import scala.io.StdIn
import scala.math._

object RaskinBobbinsBackup {

  def main(args: Array[String]): Unit = {

    val t = StdIn.readInt()
    val inputLists = for {
      _ <- 1 to t
      m = StdIn.readInt()
      n = StdIn.readInt()
      c = StdIn.readLine().split(" ").map(_.toInt)
    } {
      val cZipped = c.zipWithIndex.sortBy(_._1)
//      println(cZipped.mkString(" "))
      val res = recursion(cZipped, m)
      println(res._1 + " " + res._2)
    }

  }

  def recursion(xs: Array[(Int, Int)], m: Int): (Int, Int) = {
    xs match {
      case Array() => (-1, -1)
      case arr =>
        val (f1, i1) = arr.head
//        println(s"recursion: arr-length = ${arr.length}, i1 = $i1, f1 = $f1")
        val i2 = binarySearch(arr.tail, m - f1)
        if (i2 == -1)
          recursion(arr.tail, m)
        else
          (min(i1+1, i2+1), max(i1+1, i2+1))
    }
  }

  def binarySearch(arr: Array[(Int, Int)], el: Int): Int = {
//    println(s"binSearch: arr is ${arr.mkString}")
    var l = 0
    var r = arr.length
    var mid: Int = (r + l) / 2
    while (r - l > 1) {
//      println(s"l: $l, r: $r, mid: $mid, arr[mid]: ${arr(mid)}")
      mid = (r + l) / 2
      if (arr(mid)._1 > el)
        r = mid
      else
        l = mid
    }
//    println(s"binSearch: returning index $l, value is ${arr(l)}")
    return if (arr(l)._1 == el)
      arr(l)._2
    else
      -1
  }

}
