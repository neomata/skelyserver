package com.neomata.skelyserver.source.processing

import akka.stream.scaladsl.Flow

trait OperationStage[T, K] {
  def flow: Flow[T, K, _]
}
