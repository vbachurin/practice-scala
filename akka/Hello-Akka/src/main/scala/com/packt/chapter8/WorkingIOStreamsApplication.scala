package com.packt.chapter8

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Tcp}
import akka.util.ByteString

object WorkingIOStreamsApplication extends App {

  implicit val actorSystem = ActorSystem("WorkingIOStreams")
  implicit val actorMaterializer = ActorMaterializer()

  val maxGroups = 1000

  val connections = Tcp().bind("127.0.0.1", 1234)
  connections.runForeach(connection => connection.handleWith(wordCount))

  val wordCount = Flow[ByteString].map(_.utf8String.toUpperCase)
    .mapConcat(_.split(" ").toList)
    .collect { case w if w.nonEmpty =>
      w.replaceAll("""[p{Punct}&&[^.]]""", "")
          .replaceAll(System.lineSeparator(), "")
    }
    .groupBy(maxGroups, identity)
    .map(_ -> 1)
    .reduce((l, r) => (l._1, l._2 + r._2))
    .mergeSubstreams
    .map(x => ByteString(s"[${x._1} => ${x._2}]\n"))
}
