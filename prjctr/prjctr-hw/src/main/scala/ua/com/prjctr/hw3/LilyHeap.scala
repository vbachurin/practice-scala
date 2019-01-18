package ua.com.prjctr.hw3

import scala.collection.mutable.ArraySeq
import scala.io.StdIn

object LilyHeap {

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

  def buildHeap(arr: ArraySeq[Int], descending: Boolean) = {
    var i = heap.length / 2 - 1
    while (i >= 0) {
      heapify(arr, i, descending)
      i -= 1
    }
    heap
  }

  def heapify(arr: ArraySeq[Int], i: Int, descending: Boolean): ArraySeq[Int] = {
    var root = i
    var l = 2 * i + 1
    var r = 2 * i + 2

    val condition: Int => Boolean = { el =>
      if (descending) arr(el) > arr(root)
      else arr(el) < arr(root)
    }

    if (l < arr.length && condition(l))
      root = l

    if (r < arr.length && condition(r))
      root = r

    if (root != i) {
      swap(arr, root, i)
      heapify(arr, root, descending)
    }
    arr
  }

  def swapsToSortArray(arr: ArraySeq[Int], descending: Boolean): Int = {
    println(s"====================== descending = $descending")
    var i, counter = 0
    while (i < arr.length - 1) {
      val min = findMin(arr, i, descending)
      if (min != arr(i)) {
        swap(arr, i, 0)
        counter += 1
      }
      i += 1
    }
    counter
  }

  val heap = ArraySeq[Int]()

  def findMin(arr: ArraySeq[Int], i: Int, descending: Boolean): Int = {
    // build heap and find min in it

    buildHeap(arr.drop(i), descending)
    println(s"Min: ${heap(0)}, i: $i")
    heap(0)

//    var indexOfMin, j = i
//    while (j < arr.length) {
//      val condition =
//        if (descending) arr(j) > arr(indexOfMin)
//        else arr(j) < arr(indexOfMin)
//      if (condition)
//        indexOfMin = j
//      j += 1
//    }
//    indexOfMin
  }

  private def swap(arr: => ArraySeq[Int], sIndex: Int, tIndex: Int): Unit = {
        println(s"Swapping: [sIndex $sIndex value ${arr(sIndex)}] with [tIndex $tIndex value ${arr(tIndex)}]")
    val temp = arr(sIndex)
    arr(sIndex) = arr(tIndex)
    arr(tIndex) = temp
  }

}
