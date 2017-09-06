package com.packt.chapter5

import akka.actor.{Actor, ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class SumActor extends Actor {
  override def receive: Receive = {
    case (a: Int, b: Int) => sender ! (a + b)
  }
}

class TestSpec extends TestKit(ActorSystem("TestSpec"))
  with ImplicitSender with WordSpecLike with Matchers with BeforeAndAfterAll {
  override def afterAll(): Unit = TestKit.shutdownActorSystem(system)

  "Sum actor" must {
    "send back sum of two integers" in {
      val sumActor = system.actorOf(Props[SumActor])
      sumActor ! (10, 12)
      expectMsg(22)
    }
  }
}
