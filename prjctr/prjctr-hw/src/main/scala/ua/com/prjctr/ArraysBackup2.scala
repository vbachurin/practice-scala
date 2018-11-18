//package ua.com.prjctr
//
//import scala.io.StdIn
//
//object Arrays {
//
//  def equilibrium(inputArray: Array[Int]): Boolean = {
//    (0 to inputArray.size)
//      .map { splitter =>
//        equilibrium0(inputArray, splitter)
//      }
//      .foldLeft(false)(_ || _)
//  }
//
//  def equilibrium0(inputArray: Array[Int], splitter: Int): Boolean = {
//    inputArray.size / 2 - splitter match {
//      case ll if ll > 0 =>
//        val (lSum: Int, rSum: Int) = sumParts(inputArray, splitter)
//        if (lSum <= rSum) // AND if the sum of right part is already greater
//          false // then NO
//        else // the sum of right part is still smaller than left part
//        {
//          val minusDroppedRight = inputArray.dropRight(splitter)
//          val takenRight = minusDroppedRight.takeRight(inputArray.size - 2 * splitter - 1)
//          val newRSum = rSum + takenRight.sum
//          lSum == newRSum
//        }
//      case rr if rr < 0 =>
//        val absRR = Math.abs(rr)
//        val (lSum: Int, rSum: Int) = sumParts(inputArray, absRR)
//
//        if (lSum >= rSum) // AND if the sum of left part is already greater
//          false // then NO
//        else // the sum of left part is still smaller than right part
//        {
//          val newLSum = lSum + inputArray.drop(absRR + 1).take(splitter - absRR).sum
//          rSum == newLSum
//        }
//      case 0          =>
//        val l = inputArray.take(splitter)
//        val r = inputArray.takeRight(inputArray.size - splitter - 1)
//        val lSum = l.sum
//        val rSum = r.sum
//        lSum == rSum
//    }
//  }
//
//  private def sumParts(inputArray: Array[Int], splitter: Int) = {
//    val l = inputArray.take(splitter)
//    val r = inputArray.takeRight(splitter)
//    val lSum = l.sum
//    val rSum = r.sum
//    (lSum, rSum)
//  }
//
//  def main(args: Array[String]): Unit = {
//    val t = StdIn.readInt()
//    val inputLists = for {
//      _ <- 1 to t
//      n = StdIn.readInt()
//      arr = StdIn.readLine().split(" ").take(n).map(_.toInt)
//    } yield arr
//
//    for {
//      inputList <- inputLists
//    } {
//      equilibrium(inputList) match {
//        case true  => println("YES")
//        case false => println("NO")
//      }
//    }
//  }
//
//}
