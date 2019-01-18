package ua.com.prjctr.hw4

import scala.collection.mutable
import scala.io.StdIn

object PlayGame {

  def main(args: Array[String]): Unit = {
    val n = StdIn.readInt()
    val you = StdIn.readLine().split(" ").map(_.toInt)
    val computer = StdIn.readLine().split(" ").map(_.toInt)

    val result: Int = lineUpAndCountSurvivors(you, computer)
    println(result)
  }

  def lineUpAndCountSurvivors(you: Array[Int], computerInit: Array[Int]): Int = {
    val youSorted = you.sorted
    val computerSorted = computerInit.sorted

    var forcesLeft = you.sum

    var border = 0
    var i = youSorted.length - 1
    var j = computerSorted.length - 1
    while (i >= border) {
      if (youSorted(i) <= computerSorted(j)) {
        forcesLeft -= youSorted(border)
        border += 1
      } else
        i -= 1
      j -= 1
    }
    forcesLeft
  }

  //    val n = you.length
  //    val computerSorted = computerInit.sorted
  //    var swapped = true
  //    do {
  //      swapped = false
  //      var i = 0
  //      while(i < you.length - 1) {
  //        if (you(i) > computerSorted(i + 1) && you(i) > you(i + 1)) {
  //          swap(you, i, i + 1)
  //          swapped = true
  //        }
  //        i += 1
  //      }
  //    } while (swapped)
  //    println(you.mkString(","))
  //    120
  //
  //  }
  //  def lineUpAndCountSurvivors(you: Array[Int], computer: Array[Int]): Int = {
  //    val youSorted = you.sorted//sortWith(_ > _).toList
  //    val computerSorted = computer.sorted//sortWith(_ > _).toList

  //    val zipped = youSorted.zip(computerSorted)
  //
  //    val movesToMake = zipped.foldLeft(0){case (acc, (y, c)) => if (y <= c) acc + 1 else acc }
  //    println(movesToMake)
  //
  //    var j = 0
  //    for {
  //      i <- (you.length - 1) to (end = 1, step = -1)
  //    } {
  //      if (youSorted(i) <= computerSorted(i)) {
  //        swap(youSorted, i, i - 1)
  //        j += 1
  //      }
  //    }
  //
  //    val lineUp = youSorted.zip(computerSorted)
  //    lineUp.foreach(println)
  //    lineUp.filter{case (y, c) => y > c}.foldLeft(0){ (acc, pair) => acc + pair._1}
  //    120
  //  }



  private def swap(arr: Array[Int], sIndex: Int, tIndex: Int): Unit = {
    println(s"Swapping: [arr[$sIndex] ${arr(sIndex)}] with [arr[$tIndex] value ${arr(tIndex)}]")
    val temp = arr(sIndex)
    arr(sIndex) = arr(tIndex)
    arr(tIndex) = temp
  }

}
