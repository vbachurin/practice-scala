package com.packt.chapter6

import akka.actor.ActorLogging
import akka.persistence._

class SnapshotActor extends PersistentActor with ActorLogging {
  override def persistenceId: String = "ss-id-1"
  var state = ActiveUsers()
  def updateState(event: Event) = state = state.update(event)

  override def receiveRecover: Receive = {
    case evt: Event => updateState(evt)
    case SnapshotOffer(metadata, snapshot: ActiveUsers) => state = snapshot
    case RecoveryCompleted => log.info(s"Recovery completed. Current state: [$state]")
  }

  override def receiveCommand: Receive = {
    case UserUpdate(userId, Add) => persist(AddUserEvent(userId))(updateState)
    case UserUpdate(userId, Remove) => persist(RemoveUserEvent(userId))(updateState)
    case "snap" => saveSnapshot(state)
    case SaveSnapshotSuccess(metadata) => log.info(s"Snapshot success [$metadata")
    case SaveSnapshotFailure(metadata, e)=> log.warning(s"Snapshot failure [$metadata] Reason: [$e]")
  }

  override def aroundPostStop(): Unit = log.info("Stopping")

  override def recovery: Recovery = Recovery(SnapshotSelectionCriteria.Latest)
}
