package ua.com.prjctr.hw4

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.StdIn

object RGB {
//
//  val allCosts: Array[Array[Int]] = Array(
//    Array(11, 100, 100),
//    Array(200, 21, 200),
//    Array(300, 300, 31)
//  )

  def dups(list: List[Int]): Boolean = list match {
    case x1 :: x2 :: xs if x1 == x2 => true
    case x1 :: x2 :: xs => dups(x2 :: xs)
    case x1 :: x2 :: Nil => x1 == x2
    case _ => false
  }

  def main(args: Array[String]): Unit = {
    val n = StdIn.readInt()
    val allCosts = for {
      _ <- Array.range(1, n + 1)
      houseCosts = StdIn.readLine().split(" ").map(_.toInt)
    } yield houseCosts //.toList

    var currMin = Int.MaxValue
    dice_helper(n, ListBuffer.empty)

    def dice_helper(dice: Int, sofar: scala.collection.mutable.ListBuffer[Int]): List[Int] = {
      if (dice == 0)
        sofar.toList match {
          case list if dups(list) => List()
          case _ =>
            val sum = sofar.zipWithIndex.map { case (j, i) => {
              //              println(s"color $j cost ${allCosts(i)(j)}")
              allCosts(i)(j)
            }
            }.sum
            //            println(sum)
            currMin = Math.min(sum, currMin)
            sofar.toList

        }
      else {
        for {
          i <- 0 to 2
        } {
          sofar += i
          dice_helper(dice - 1, sofar)
          sofar.remove(sofar.size - 1)
        }
        sofar.toList
      }
    }

    println(currMin)
  }


  def calculateResult(allCosts: Array[Array[Int]]) = {
    var i = 0
    val sums = new mutable.MutableList[Int]()

    while (i < allCosts.length) {
      var k = 0
      var prevJ = Int.MaxValue
      var sum = 0
      while (k < allCosts.length) {
        var j = 0
        while (j < 3) {
          if (j != prevJ) {
            sum += allCosts(k)(j)
            prevJ = j
            j = 3
          }
          j += 1
        }
        k += 1
      }
      sums += sum
      i += 1
    }
    sums

  }

  //  def calculateResult(allCosts: IndexedSeq[List[Int]]): Int = {
  //    val mappedAndSorted = allCosts.toList.map(house =>
  //      house.map((_.toInt)).zip("RGB").sorted
  //    )
  //    minimalTotalCost(mappedAndSorted)
  //  }

  def minimalTotalCost(allCosts: List[List[(Int, Char)]]): Int = {
    allCosts match {
      case Nil => 0
      case (x :: xs) :: houses =>
        recursion(houses, x :: Nil, xs)
    }
  }

  def recursion(allCosts: List[List[(Int, Char)]], path: List[(Int, Char)], otherColors: List[(Int, Char)]): Int = {
    allCosts match {
      case Nil =>
        path.foreach(println)
        path.map(_._1).sum
      case (x1 :: x2 :: xs) :: houses
        if (x2._1 - x1._1 > otherColors.head._1 - path.head._1) =>
        recursion(houses, (otherColors.head._1 - path.head._1, otherColors.head._2) :: path, xs)
      case (x :: xs) :: houses
        if (x._2 != path.head._2) =>
        recursion(houses, x :: path, xs)
      case (x :: xs) :: houses =>
        recursion(houses, xs.head :: path, xs)
      //      case ((cost1, color1) :: (cost2, color2) :: (cost3, color3) :: Nil) :: others =>

    }
  }

  //  private def swap(arr: Array[Int], sIndex: Int, tIndex: Int): Unit = {
  //    println(s"Swapping: [arr[$sIndex] ${arr(sIndex)}] with [arr[$tIndex] value ${arr(tIndex)}]")
  //    val temp = arr(sIndex)
  //    arr(sIndex) = arr(tIndex)
  //    arr(tIndex) = temp
  //  }
}
