package ua.com.prjctr

import scala.io.StdIn

object Squares {

  def main(args: Array[String]): Unit = {
    val t = StdIn.readInt()
    val inputList = for {
      _ <- 1 to t
      ab = StdIn.readLine().split(" ").map(_.toInt)
    } yield (ab(0), ab(1))

    for {
      (a, b) <- inputList
    } {
      val result = res(a, b)
      println(result)
    }
  }

  def squares(a: Long, b: Long): Seq[Long] = {
    Stream
      .range[Long](1, 1000000000)
      .map(x => x * x)
      .dropWhile(_ < a)
      .takeWhile(_ <= b)
  }

  def res(a: Long, b: Long) = squares(a, b).size
}
