package com.packt.chapter3

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.routing.{ScatterGatherFirstCompletedPool, TailChoppingPool}
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.Random

object TailChoppingpool extends App {
  implicit val timeout = Timeout(10 seconds)
  val actorSystem = ActorSystem("Hello-Akka")
  val router =
    actorSystem.actorOf(TailChoppingPool(5, within = 10 seconds, interval = 20 millis)
    .props(Props[TailChoppingActor]))
  println(Await.result((router ? "yello").mapTo[String], 10 seconds))

}

class TailChoppingActor extends Actor {
  override def receive: Receive = {
    case msg: String => sender ! s"${self.path.name} says $msg back to you"
    case _ => println("I don't understand the message")
  }
}
