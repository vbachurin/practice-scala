package ua.com.prjctr.binsearch

import scala.io.StdIn

object OneTenHundredBackup {

  def loop(v: Int): Stream[Int] = v #:: loop(v * 10)

  def main(args: Array[String]): Unit = {
    val n = StdIn.readInt()
    val stream = loop(1).flatMap(x => s"$x")
    val ints = for {
      _ <- 1 to n
      i = StdIn.readInt()
    } yield i

    val max = ints.max
    val str = stream.take(max).mkString
    val chars = for {
      j <- ints
    } yield str.charAt(j - 1)

    println(chars.mkString("", " ", ""))
    }
  }
