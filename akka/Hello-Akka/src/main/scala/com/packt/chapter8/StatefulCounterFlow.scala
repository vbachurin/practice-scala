package com.packt.chapter8

import akka.stream.{Attributes, FlowShape, Inlet, Outlet}
import akka.stream.stage.{GraphStage, GraphStageLogic, InHandler, OutHandler}
import com.packt.chapter8.WorkingWithGraphs.GenericMsg

class StatefulCounterFlow extends GraphStage[FlowShape[Seq[GenericMsg], Int]] {

  val in: Inlet[Seq[GenericMsg]] = Inlet("IncomingGenericMsg")
  val out: Outlet[Int] = Outlet("OutgoingCount")
  override val shape: FlowShape[Seq[GenericMsg], Int] = FlowShape(in, out)

  override def createLogic(inheritedAttributes: Attributes): GraphStageLogic =
    new GraphStageLogic(shape) {
      var count = 0

      setHandler(in, new InHandler {
        override def onPush(): Unit = {
          val elem = grab(in)
          count += elem.size
          push(out, count)
        }
      })

      setHandler(out, new OutHandler {
        override def onPull(): Unit = {
          pull(in)
        }
      })
    }
}
