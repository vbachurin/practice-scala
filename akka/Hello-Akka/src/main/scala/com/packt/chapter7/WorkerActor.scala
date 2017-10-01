package com.packt.chapter7

import akka.actor.Actor

case class Work(workId: String)
case class WorkDone(workId: String)

class WorkerActor extends Actor {
  override def receive = {
    case Work(workId) =>
      Thread.sleep(3000)
      sender ! WorkDone(workId)
      println(s"Work $workId was done by worker actor")
  }
}
