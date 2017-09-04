package com.packt.chapter3

import akka.actor.{Actor, ActorSystem, Props}
import akka.actor.Actor.Receive
import akka.routing.RandomPool

class RandomPoolActor extends Actor {
  override def receive: Receive = {
    case msg: String => println(s"I am ${self.path.name}")
    case _ => println(s"I don't understand the message")
  }
}

object Randompool extends App {
  val actorSystem = ActorSystem("Hello-Akka")
  val router = actorSystem.actorOf(RandomPool(5).props(Props[RandomPoolActor]))
  for (i <- 1 to 5)
    router ! s"Hello $i"
}
