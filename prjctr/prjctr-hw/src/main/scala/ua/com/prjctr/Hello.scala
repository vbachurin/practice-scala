package ua.com.prjctr

import scala.io.StdIn

object Hello extends Greeting with App {
  val t = StdIn.readInt()
  val inputList = for {
    _ <- 1 to t
    nms = StdIn.readLine().split(" ").map(_.toInt)
  } yield (nms(0), nms(1), nms(2))

  for {
    (n, m, s) <- inputList
  } {
    val result = (s - 1 + m) % n
    println(result)
  }

}

trait Greeting {
  lazy val greeting: String = "hello"
}

/*
2
5 2 1
5 2 2
 */