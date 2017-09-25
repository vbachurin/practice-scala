package com.packt.chapter7

import akka.actor.{ActorSystem, Address, Deploy, Props}
import akka.remote.RemoteScope

object RemoteActorProgrammatically1 extends App {
  val actorSystem = ActorSystem("RemoteActorsProgrammatically1")
}

object RemoteActorProgrammatically2 extends App {
  val actorSystem = ActorSystem("RemoteActorsProgrammatically2")
  println("Creating actor from RemoteActorsProgrammatically2")
  val address = Address("akka.tcp", "RemoteActorsProgrammatically1", "127.0.0.1", 2552)
  val actor = actorSystem.actorOf(Props[SimpleActor].withDeploy(Deploy(scope = RemoteScope(address))), "remoteActor")
  actor ! "Checking"

}
