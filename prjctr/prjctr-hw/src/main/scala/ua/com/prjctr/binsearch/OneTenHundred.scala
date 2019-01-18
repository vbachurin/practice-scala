package ua.com.prjctr.binsearch

import scala.io.StdIn

object OneTenHundred {

  def main(args: Array[String]): Unit = {
    val n = StdIn.readInt()

    val results = for {
      _ <- 1 to n
      j = StdIn.readInt()
    } yield {
      var i = 1
      var a = j
      while (i < a) {
        a -= i
        i += 1
      }
      if (a == 1)
        1
      else
        0
    }

    println(results.mkString("", " ", ""))

  }
}
