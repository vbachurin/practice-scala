package ua.com.prjctr.hw3

import scala.collection.mutable.ArraySeq
import scala.io.StdIn

object Lily {

  def main(args: Array[String]): Unit = {
    val _ = StdIn.readInt()
    val arr = StdIn.readLine().split(" ").map(_.toInt)
    val res = lilysHomework(arr)
    println(res)
  }

  def lilysHomework(arr: Array[Int]): Int = {
    Math.min(
      swapsToSortArray(ArraySeq(arr).flatten, descending = false),
      swapsToSortArray(ArraySeq(arr).flatten, descending = true)
    )
  }

  def swapsToSortArray(arr: ArraySeq[Int], descending: Boolean): Int = {
    var i, counter = 0
    while (i < arr.length - 1) {
      val indexOfMin = findMin(arr, i, descending)
      if (indexOfMin != i) {
        swap(arr, i, indexOfMin)
        counter += 1
      }
      i += 1
    }
    counter
  }


  def findMin(arr: ArraySeq[Int], i: Int, descending: Boolean): Int = {
    var indexOfMin, j = i
    while (j < arr.length) {
      val condition =
        if (descending) arr(j) > arr(indexOfMin)
        else arr(j) < arr(indexOfMin)
      if (condition)
        indexOfMin = j
      j += 1
    }
    indexOfMin
  }

  private def swap(arr: => ArraySeq[Int], sIndex: Int, tIndex: Int): Unit = {
//    println(s"Swapping: [sIndex $sIndex value ${arr(sIndex)}] with [tIndex $tIndex value ${arr(tIndex)}]")
    val temp = arr(sIndex)
    arr(sIndex) = arr(tIndex)
    arr(tIndex) = temp
  }

}
