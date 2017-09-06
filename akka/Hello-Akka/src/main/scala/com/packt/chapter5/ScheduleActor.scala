package com.packt.chapter5

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorSystem, Props}

import scala.concurrent.duration._

class RandomIntAdder extends Actor {
  val r = scala.util.Random
  override def receive: Receive = {
    case "tick" =>
      val randomIntA = r.nextInt(10)
      val randomIntB = r.nextInt(10)
      println(s"sum of $randomIntA and $randomIntB is ${randomIntA + randomIntB}")
  }
}

object ScheduleActor extends App {
  val system = ActorSystem("Hello-Akka")
  import system.dispatcher
  val actor = system.actorOf(Props[RandomIntAdder])
  system.scheduler.scheduleOnce(10 seconds, actor, "tick")
  system.scheduler.schedule(11 seconds, 2 seconds, actor, "tick")
}
