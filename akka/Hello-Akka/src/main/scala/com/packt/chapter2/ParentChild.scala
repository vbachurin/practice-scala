package com.packt.chapter2

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorSystem, Props}

private [this] case object CreateChild
case class Greet(msg: String)

class ChildActor extends Actor {
  override def receive: Receive = {
    case Greet(msg) => println(s"My parent[${self.path.parent}] greeted to me [${self.path}] $msg")
  }
}

class ParentActor extends Actor {
  override def receive: Receive = {
    case CreateChild =>
      val child = context.actorOf(Props[ChildActor], "child")
      child ! Greet("Hello Child")
  }
}

object ParentChild extends App {
  val actorSystem = ActorSystem("Supervision")
  val parent = actorSystem.actorOf(Props[ParentActor1], "parent")
  parent ! CreateChild
}
