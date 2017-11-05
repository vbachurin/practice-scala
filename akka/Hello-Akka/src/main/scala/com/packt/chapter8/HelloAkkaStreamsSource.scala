package com.packt.chapter8

import akka.stream.{Attributes, Outlet, SourceShape}
import akka.stream.stage.{GraphStage, GraphStageLogic, OutHandler}

class HelloAkkaStreamsSource extends GraphStage[SourceShape[String]] {

  val out: Outlet[String] = Outlet("SystemInputSource")
  override def shape = SourceShape(out)

  override def createLogic(inheritedAttributes: Attributes) = new GraphStageLogic(shape) {
    setHandler(out, new OutHandler {
      override def onPull(): Unit = {
        val line = "Hello World Akka Streams!"
        push(out, line)
      }
    })
  }

}
