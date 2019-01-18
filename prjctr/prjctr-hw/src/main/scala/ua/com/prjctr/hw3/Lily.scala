package ua.com.prjctr.hw3


import scala.collection.mutable
import scala.io.StdIn
import scala.collection.mutable.ArraySeq

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
    val heap = buildHeap(arr.clone().zipWithIndex, descending)
    var i, counter = 0
    var j = heap.length - 1
    while (i < arr.length - 1) {
      val (minValue, minIndex) = heap(0)
      if (arr(i) != minValue) {
        swapArr(arr, i, minIndex)
        counter += 1
        println(s"${arr(minIndex)} was on position ${heap(i)._2}, moved to position $minIndex. \nUpdating heap[$minIndex] ${heap(minIndex)} to (${arr(minIndex)}, $minIndex)")
        heap(minIndex) = (arr(minIndex), minIndex)
        swapHeap(heap, 0, j)
      } else
        swapHeap(heap, 0, j)
      heapify(heap, j, 0, descending)
      i += 1
      j -= 1
    }
    println(s"$arr !! answer for descending = $descending is $counter")
    counter
  }

  def buildHeap(heap: mutable.ArraySeq[(Int, Int)], descending: Boolean): ArraySeq[(Int, Int)] = {
    var ii = heap.length / 2 - 1
    while (ii >= 0) {
      heapify(heap, heap.length, ii, descending)
      ii -= 1
    } // heap built
    println(s"Heap built: $heap")
    heap
  }

  private def swapArr(arr: => ArraySeq[Int], sIndex: Int, tIndex: Int): Unit = {
    println(s"Swapping in array: [arr[$sIndex] ${arr(sIndex)}] with [arr[$tIndex] ${arr(tIndex)}]")
    val temp = arr(sIndex)
    arr(sIndex) = arr(tIndex)
    arr(tIndex) = temp
  }

  private def swapHeap(arr: => ArraySeq[(Int, Int)], sIndex: Int, tIndex: Int): Unit = {
//    println(s"Swapping in heap : [sIndex $sIndex value ${arr(sIndex)}] with [tIndex $tIndex value ${arr(tIndex)}]")
    val temp = arr(sIndex)
    arr(sIndex) = arr(tIndex)
    arr(tIndex) = temp
  }

  def heapify(arr: ArraySeq[(Int, Int)], n:Int, i: Int, descending: Boolean): ArraySeq[(Int, Int)] = {
    var root = i
    var l = 2 * i + 1
    var r = 2 * i + 2

    val condition: Int => Boolean = { el =>
      if (descending) arr(el)._1 > arr(root)._1
      else arr(el)._1 < arr(root)._1
    }

    if (l < n && condition(l))
      root = l

    if (r < n && condition(r))
      root = r

    if (root != i) {
      swapHeap(arr, root, i)
      heapify(arr, n, root, descending)
    }
    arr
  }
}
