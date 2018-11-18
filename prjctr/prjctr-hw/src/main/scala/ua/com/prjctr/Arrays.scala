package ua.com.prjctr

import scala.io.StdIn

object Arrays {

    def equilibrium(inputList: Array[Int]): Option[Boolean] = {
      Stream.range(0, inputList.length)
        .map { splitter =>
          val (l, r) = inputList.splitAt(splitter)
          val ll = l.sum
          val rr = r.drop(1).sum
          ll == rr
        }
//        .dropWhile(_ == false).take(1)
        .collectFirst{case true => true}
    }

  def main(args: Array[String]): Unit = {
    val t = StdIn.readInt()
    val inputLists = for {
      _ <- 1 to t
      n = StdIn.readInt()
      arr = StdIn.readLine().split(" ").take(n).map(_.toInt)
    } yield arr

    for {
      inputList <- inputLists
    } {
      equilibrium(inputList) match {
        case Some(true)  => println("YES")
        case _ => println("NO")
      }
    }
  }

}
