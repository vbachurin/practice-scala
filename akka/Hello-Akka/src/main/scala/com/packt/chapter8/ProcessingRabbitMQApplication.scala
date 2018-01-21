package com.packt.chapter8

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.alpakka.amqp.scaladsl.{AmqpSink, AmqpSource}
import akka.stream.alpakka.amqp.{AmqpCredentials, AmqpDetailsConnectionProvider, AmqpSinkSettings, BindingDeclaration, ExchangeDeclaration, NamedQueueSourceSettings, OutgoingMessage, QueueDeclaration, Seq}
import akka.util.ByteString

object ProcessingRabbitMQApplication extends App {

  implicit val actorSystem = ActorSystem("SimpleStream")
  implicit val actorMaterializer = ActorMaterializer()

  val consumerQueueName = "akka_streams_consumer_queue"
  val consumerQueueDeclaration = QueueDeclaration(consumerQueueName)
  val sourceDeclarations = Seq(consumerQueueDeclaration)

  val exchangeName = "akka_streams_exchange"
  val exchangeDeclaration = ExchangeDeclaration(exchangeName, "direct")
  val destinationQueueName = "akka_streams_destination_queue"
  val destinationQueueDeclaration = QueueDeclaration(destinationQueueName)
  val bindingDeclaration = BindingDeclaration(destinationQueueName, exchangeName)
  val sinkDeclarations = Seq(exchangeDeclaration, destinationQueueDeclaration, bindingDeclaration)

  val connectionSetting = AmqpDetailsConnectionProvider(
    credentials = Some(AmqpCredentials("guest", "guest")),
    hostAndPortList = List(("localhost", 5672))
  )
  val amqpSourceConfig = NamedQueueSourceSettings(connectionSetting, consumerQueueName, sourceDeclarations)
  val rabbitMQSource = AmqpSource.atMostOnceSource(amqpSourceConfig, 1000)
  val amqpSinkConfig = AmqpSinkSettings(connectionSetting, Some(exchangeName), None, sinkDeclarations)
  val rabbitMQSink = AmqpSink(amqpSinkConfig)

  val stream = rabbitMQSource
    .map(incomingMessage => {
      val upperCased = incomingMessage.bytes.utf8String.toUpperCase
      OutgoingMessage(
        bytes = ByteString(upperCased),
        immediate = false,
        mandatory = false,
        props = None
      )
    })
    .to(rabbitMQSink)

  stream.run()

}
