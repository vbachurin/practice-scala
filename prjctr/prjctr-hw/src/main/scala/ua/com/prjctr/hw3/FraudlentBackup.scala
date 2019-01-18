package ua.com.prjctr.hw3

import scala.util.Sorting


class FraudlenBackupt(val expenditure: Array[Int]) {

  var counts: Array[(Int, Int)] = Array.empty
  var prev: Int = Int.MinValue

  val min = 0
  val max = 200

  def activityNotifications(d: Int): Int = {
    var i = d
    var counter = 0
    while (i < expenditure.length) {
      if (expenditure(i) >= findMedian(expenditure.slice(i - d, i)) * 2)
        counter += 1
      i += 1
    }
    counter
  }

  def findMedian(slice: Array[Int]): Double = {
    val sortedSlice = slice
    Sorting.quickSort(sortedSlice)
    //    val sortedSlice = countSort(slice)
    val n = sortedSlice.length
    if (n % 2 == 0) {
      val mean = (sortedSlice(n / 2 - 1).toDouble + sortedSlice(n / 2).toDouble) / 2
      mean
    }
    else
      sortedSlice(n / 2)
  }

  def countSort(input: Array[Int], min: Int = 0, max: Int = 200): List[Int] = {
    if (counts.isEmpty)
      counts = input.foldLeft(Array.fill(max - min + 1)(0)) { (arr, n) =>
        arr(n - min) += 1
        arr
      }.zipWithIndex //.reverse
    else {
      val (prevCnt, prevIdx) = counts(prev)
      counts(prev) = (prevCnt - 1, prevIdx)
      val (newCnt, newIdx) = counts(input(input.length - 1))
      counts(input(input.length - 1)) = (newCnt + 1, newIdx)
    }
    prev = input(0)
    counts.foldLeft(List[Int]()) {
      case (lst, (cnt, ndx)) => List.fill(cnt)(ndx + min) ::: lst
    }
  }

}


