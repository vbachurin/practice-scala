package ua.com.prjctr.hw3

import java.io._


object FraudlentCounting {

  var counts: Array[(Int, Int)] = Array.empty
  var prev: Int = Int.MinValue

  def activityNotifications(expenditure: Array[Int], d: Int): Int = {
    var i = d
    var counter = 0
    while (i < expenditure.length) {
      if (expenditure(i) >= findMedian(expenditure.slice(i - d, i)) * 2)
        counter += 1
      i += 1
    }
    counter
  }

  def findMedian(initExpenditure: Array[Int]): Double = {
//    val expenditure = initExpenditure
//    Sorting.quickSort(expenditure)
    val expenditure = countSort(initExpenditure, 0, 200)
    val n = expenditure.length
    if (n % 2 == 0) {
      val mean = (expenditure(n / 2 - 1).toDouble + expenditure(n / 2).toDouble) / 2
      mean
    }
    else
      expenditure(n / 2)
  }

  def countSort(input: Array[Int], min: Int, max: Int): List[Int] = {
    if (counts.isEmpty)
      counts = input.foldLeft(Array.fill(max - min + 1)(0)) { (arr, n) =>
        arr(n - min) += 1
        arr
      }.zipWithIndex//.reverse
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

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val nd = stdin.readLine.split(" ")

    val n = nd(0).trim.toInt

    val d = nd(1).trim.toInt

    val expenditure = stdin.readLine.split(" ").map(_.trim.toInt)
    val result = activityNotifications(expenditure, d)

    printWriter.println(result)

    printWriter.close()
  }

}
