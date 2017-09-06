package com.packt.chapter6

import akka.persistence.{PersistentActor, SnapshotOffer}

class SamplePersistenceActor extends PersistentActor {
  var state = ActiveUsers()

  def updateState(event: Event) = state = state.update(event)

  override def receiveRecover: Receive = {
    case evt: Event => updateState(evt)
    case SnapshotOffer(_, snapshot: ActiveUsers) => state = snapshot
  }

  override def receiveCommand: Receive = {
    case UserUpdate(userId, Add) => persist(AddUserEvent(userId))(updateState)
    case UserUpdate(userId, Remove) => persist(RemoveUserEvent(userId))(updateState)
    case "snap" => saveSnapshot(state)
    case "print" => println(state)
  }

  override def persistenceId: String = "unique-id-1"
}
