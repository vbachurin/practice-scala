package com.packt.chapter6

import akka.actor.ActorSystem
import akka.persistence.Recovery
import akka.persistence.query.journal.leveldb.scaladsl.LeveldbReadJournal
import akka.persistence.query.{EventEnvelope, PersistenceQuery}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink



import scala.concurrent.duration._

object FriendJournalReader extends App {
  implicit val system = ActorSystem()
  import system.dispatcher
  implicit val mat = ActorMaterializer()(system)
  val queries = PersistenceQuery(system).readJournalFor[LeveldbReadJournal](LeveldbReadJournal.Identifier)

  val laura = system.actorOf(FriendActor.props("Laura", Recovery()))
  val maria = system.actorOf(FriendActor.props("Maria", Recovery()))
  laura ! AddFriend(Friend("Hector"))
  laura ! AddFriend(Friend("Nancy"))
  maria ! AddFriend(Friend("Oliver"))
  maria ! AddFriend(Friend("Steve"))
  system.scheduler.scheduleOnce(5 seconds, maria, AddFriend(Friend("Steve")))
  system.scheduler.scheduleOnce(10 seconds, maria, RemoveFriend(Friend("Oliver")))
  Thread.sleep(2000)

  queries.allPersistenceIds().map(id => system.log.info(s"Id received [$id]")).to(Sink.ignore).run()
  queries.eventsByPersistenceId("Laura").map((e: EventEnvelope) => log(e.persistenceId, e.event)).to(Sink.ignore).run()
  queries.eventsByPersistenceId("Maria").map(e => log(e.persistenceId, e.event)).to(Sink.ignore).run()

  def log(id: String, evt: Any) = system.log.info(s"Id [$id] Event [$evt]")

}
