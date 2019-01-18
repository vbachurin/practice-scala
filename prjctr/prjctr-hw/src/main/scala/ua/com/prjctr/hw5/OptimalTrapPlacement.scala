package ua.com.prjctr.hw5

import java.util
import java.util.Arrays

import scala.io.StdIn

object OptimalTrapPlacement {

  def main(args: Array[String]) {
    val n = StdIn.readLine.trim.toInt

    val a = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)

    val maxTrappingValue = trapCorridor(a, n)

    println(maxTrappingValue)
  }


  def trapCorridor(cost: Array[Int], n: Int): Long = {
    cost match {
      case Array() => 0
      case Array(only) => only
      case Array(first, second) => Math.max(first, second)
      case arr =>
        val runningTotals = Array.fill(n + 2)(0l)

        for {
          i <- 2 until runningTotals.size
        } {
          runningTotals(i) = runningTotals(i - 2) + arr(i - 2)
          if (runningTotals(i - 1) > runningTotals(i))
            runningTotals(i) = runningTotals(i - 1)
        }
        runningTotals.last
    }
  }
}
