package ua.com.prjctr.hw3

import scala.io.StdIn

object Difference {


  def main(args: Array[String]): Unit = {
    val n = StdIn.readInt()
    val a = StdIn.readLine().split(" ").map(_.toInt)
    val res = closestNumbers(a)
    println(res.map{case (one, two) => s"$one $two"}.mkString(" "))
  }

  def closestNumbers(a: Array[Int]): List[(Int, Int)] = {
    val as = a.toList.sorted
    val zipped = Int.MinValue :: as zip as
    val minDelta = zipped.map{case (one, two) => Math.abs(two - one)}.min
    zipped.filter({case (one, two) => Math.abs(two - one) == minDelta})
  }

}
