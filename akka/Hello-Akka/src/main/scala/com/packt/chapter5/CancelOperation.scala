package com.packt.chapter5

import akka.actor.{Actor, ActorSystem, Cancellable, Props}
import scala.concurrent.duration._
class CancelOperation extends Actor {
  var i = 10
  override def receive: Receive = {
    case "tick" =>
      println("Hi, do you know I do the same tasks again and again")
      i = i - 1
      if (i == 0) Scheduler.cancellable.cancel()
  }
}

object Scheduler extends App {
  val system = ActorSystem("Hello-Akka")
  import system.dispatcher
  val actor = system.actorOf(Props[CancelOperation])
  val cancellable: Cancellable = system.scheduler.schedule(0 seconds, 2 seconds, actor, "tick")
}
