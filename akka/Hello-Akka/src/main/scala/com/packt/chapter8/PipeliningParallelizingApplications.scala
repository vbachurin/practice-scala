package com.packt.chapter8

import akka.stream.scaladsl.Flow

object SyncronousPipeliningApplication extends PipeliningParallelizing {
  runGraph(Flow[Wash].via(washStage).via(dryStage))
}

object AsyncronousPipeliningApplication extends PipeliningParallelizing {
  runGraph(Flow[Wash].via(washStage.async).via(dryStage.async))
}

object ParallelizingAplication extends PipeliningParallelizing {
  runGraph(Flow[Wash].via(parallelStage))
}
