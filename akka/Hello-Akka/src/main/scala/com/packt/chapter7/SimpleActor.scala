package com.packt.chapter7

import akka.actor.Actor

class SimpleActor extends Actor {
  override def receive = {
    case _ => println(s"I have been created at ${self.path.address.hostPort}")
  }
}
