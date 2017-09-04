package com.packt.chapter4

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

object AddFuture extends App {
  val future = Future(1 + 2).mapTo[Int]
  import scala.concurrent.duration._
  val sum = Await.result(future, 10 seconds)
  println(s"Future Result $sum")
}
