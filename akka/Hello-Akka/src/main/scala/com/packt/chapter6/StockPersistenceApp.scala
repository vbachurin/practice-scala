package com.packt.chapter6

import akka.actor.ActorSystem

trait AkkaHelper {
  val system = ActorSystem("example")
  val teslaStockActor = system.actorOf(StockPersistentActor.props("TSLA"))
}

object StockApp extends App with AkkaHelper {
  teslaStockActor ! ValueUpdate(305.12)
  teslaStockActor ! ValueUpdate(305.15)
  teslaStockActor ! "print"
  Thread.sleep(5000)
  system.terminate
}

object StockRecoveryApp extends App with AkkaHelper {
  teslaStockActor ! ValueUpdate(305.20)
  teslaStockActor ! "print"
  Thread.sleep(2000)
  system.terminate

}
