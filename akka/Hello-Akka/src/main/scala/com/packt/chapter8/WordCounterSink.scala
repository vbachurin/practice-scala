package com.packt.chapter8

import akka.stream.{Attributes, Inlet, SinkShape}
import akka.stream.stage.{GraphStage, InHandler, TimerGraphStageLogic}

class WordCounterSink extends GraphStage[SinkShape[String]] {
  val in: Inlet[String] = Inlet("WordCountSink")
  override def shape = SinkShape(in)

  override def createLogic(inheritedAttributes: Attributes) = new TimerGraphStageLogic(shape) {
    var counts = Map.empty[String, Int].withDefaultValue(0)

    override def preStart(): Unit = {
      import scala.concurrent.duration._
      schedulePeriodically(None, 5 seconds)
      pull(in)
    }

    setHandler(in, new InHandler {
      override def onPush(): Unit = {
        val word = grab(in)
        counts += word -> (counts(word) + 1)
        pull(in)
      }
    })

    override protected def onTimer(timerKey: Any): Unit =
      println(s"At ${System.currentTimeMillis()} count map is $counts")
  }

}
