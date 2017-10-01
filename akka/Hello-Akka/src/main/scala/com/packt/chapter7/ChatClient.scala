package com.packt.chapter7

import akka.actor.{Actor, ActorRef, Props}
import akka.pattern.{ask, pipe}
import akka.util.Timeout
import com.packt.chapter7.ChatServer.{Connect, Disconnect, Disconnected, Message}

object ChatClient {
  def props(chatServer: ActorRef) = Props(new ChatClient(chatServer))
}
class ChatClient(chatServer: ActorRef) extends Actor {
  import context.dispatcher
  import scala.concurrent.duration._
  implicit val timeout = Timeout(5 seconds)


  override def preStart(): Unit = chatServer ! Connect

  override def receive = {
    case Disconnect =>
      (chatServer ? Disconnect).pipeTo(self)
    case Disconnected =>
      context.stop(self)
    case body: String =>
      chatServer ! Message(self, body)
    case msg: Message =>
      println(s"Message from [${msg.author}] at [${msg.creationTimestamp}]: ${msg.body}")
  }
}

