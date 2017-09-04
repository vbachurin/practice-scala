package com.packt.chapter4

import akka.actor.{Actor, ActorSystem, Props}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class FutureActor extends Actor {
  import context.dispatcher
  override def receive: Receive = {
    case (a: Int, b: Int) => val f = Future(a + b)
      val res = Await.result(f, 10 seconds)
      println(s"Future result is $res")
  }
}

object FutureInsideActor extends App{
  val actorSystem = ActorSystem("Hello-Akka")
  val fActor = actorSystem.actorOf(Props[FutureActor])
  fActor ! (10, 20)
}
