package ua.com.prjctr.hw3

import scala.collection.mutable
import scala.io.StdIn
import scala.util.Sorting

object LilyQuick {

  def main(args: Array[String]): Unit = {
    val _ = StdIn.readInt()
    val arr = StdIn.readLine().split(" ").map(_.toInt)
    val res = lilysHomework(arr)
    println(res)
  }

  def lilysHomework(arr: Array[Int]): Int = {
    val sortedAscending = arr.clone()
    Sorting.quickSort(sortedAscending)
    val asc = swapsToSortArray(arr.clone(), sortedAscending)
    val desc = swapsToSortArray(arr.clone(), sortedAscending.reverse)
    Math.min(asc, desc)
  }

  def swapsToSortArray(arr: Array[Int], sorted: Array[Int]): Int = {
    var i, counter = 0
    val positions = mutable.Map(arr.zipWithIndex: _*)
    while (i < arr.length - 1) {
      if (sorted(i) != arr(i)) {
        val minOrMaxPosition = positions(sorted(i))
        positions(arr(i)) = minOrMaxPosition
        swap(arr, i, minOrMaxPosition)
        counter += 1
      }
      i += 1
    }
    counter
  }

  private def swap(arr: Array[Int], sIndex: Int, tIndex: Int): Unit = {
//    println(s"Swapping: [arr[$sIndex] ${arr(sIndex)}] with [arr[$tIndex] value ${arr(tIndex)}]")
    val temp = arr(sIndex)
    arr(sIndex) = arr(tIndex)
    arr(tIndex) = temp
  }

}
