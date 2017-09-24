package com.packt.chapter6

import akka.actor.{ActorLogging, Props}
import akka.persistence.{PersistentActor, RecoveryCompleted}

object StockPersistentActor {
  def props(stockId: String) = Props(new StockPersistentActor(stockId))
}

class StockPersistentActor(stockId: String) extends PersistentActor with ActorLogging {
  var state = StockHistory()

  def updateState(event: ValueAppended) = state = state.update(event)

  override def receiveRecover: Receive = {
    case evt: ValueAppended => updateState(evt)
    case RecoveryCompleted => log.info(s"Recovery completed. Current state: $state")
  }

  override def receiveCommand: Receive = {
    case ValueUpdate(value) => persist(ValueAppended(StockValue(value)))(updateState)
    case "print" => log.info(s"Current state: $state")
  }

  override def persistenceId: String = stockId
}
