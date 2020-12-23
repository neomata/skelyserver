package com.neomata.skelyserver.source.processing.operator

import akka.http.scaladsl.model.HttpEntity.ChunkStreamPart
import akka.stream.scaladsl.Flow
import akka.util.ByteString
import com.neomata.skelyserver.source.processing.OperationStage

object ChunkOperator {
  def apply: ChunkOperator = new ChunkOperator
}

class ChunkOperator extends OperationStage[ByteString, ChunkStreamPart] {
  override def flow: Flow[ByteString, ChunkStreamPart, _] = Flow[ByteString].map(chunk => ChunkStreamPart(chunk))
}