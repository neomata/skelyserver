package com.neomata.skelyserver.source

import akka.http.scaladsl.model.HttpEntity.ChunkStreamPart
import akka.stream.scaladsl.Source

trait VideoResourceEntity {
  def nextFrame: Source[ChunkStreamPart, _]

  def stream: Source[ChunkStreamPart, _]
}
