package com.packt.chapter2

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.util.Random

case class DoubleValue(x: Int)
private [this] case object CreateChild1
case object Send
case class Response(x: Int)

class DoubleActor extends Actor {
  override def receive: Receive = {
    case DoubleValue(number) =>
      println(s"${self.path.name} Got the number $number")
      sender ! Response(number * 2)
  }
}

class ParentActor1 extends Actor {
  val random = new Random
  var childs = scala.collection.mutable.ListBuffer[ActorRef]()

  override def receive: Receive = {
    case CreateChild1 => childs ++= List(context.actorOf(Props[DoubleActor]))
    case Send =>
      println(s"Sending messages to child")
      childs.zipWithIndex map {case (child, value) => child ! DoubleValue(random.nextInt(10))}
    case Response(x) => println(s"Parent: Response from child ${sender.path.name} is $x")
  }
}

object SendMesagesToChilds extends App {
  val actorSystem = ActorSystem("Hello-Akka")
  val parent = actorSystem.actorOf(Props[ParentActor1], "parent")
  parent ! CreateChild1
  parent ! CreateChild1
  parent ! CreateChild1
  parent ! Send
}
