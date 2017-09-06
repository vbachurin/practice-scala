package com.packt.chapter5

import akka.actor.ActorSystem
import scala.concurrent.duration._

object ScheduleOperation extends App {
  val system = ActorSystem("Hello-Akka")
  import system.dispatcher
  system.scheduler.scheduleOnce(10 seconds) {
    println(s"Sum of (1 + 2) is ${1 + 2}")
  }
  system.scheduler.schedule(11 seconds, 2 seconds) {
    println("Hello, sorry for disturbing you every 2 seconds")
  }
}
