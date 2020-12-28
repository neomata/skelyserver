package com.neomata.skelyserver.server

import java.io.File
import java.nio.file.Paths

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.HttpEntity.ChunkStreamPart
import akka.stream.scaladsl.{FileIO, Source}
import akka.util.ByteString
import com.neomata.skelyserver.source.processing.operator.ChunkOperator
import com.typesafe.scalalogging.StrictLogging

object ResourceHandler {
  def apply(directory: String)(implicit system: ActorSystem[_]): ResourceHandler = {
    new ResourceHandler(directory)(system)
  }
}

class ResourceHandler(var directory: String)(implicit system: ActorSystem[_]) extends StrictLogging {

  def octetStream(name: String): Source[ChunkStreamPart, _] = {
    logger.info("Attempting to fetch - {} from directory - {}", name, directory)
    val fileMap = this.filesInDirectoryMap(directory)
    fileMap.get(name) match {
      case Some(value) => FileIO.fromPath(Paths.get(value.getAbsolutePath)).via(ChunkOperator.apply.flow)
      case None => this.emptyData().via(ChunkOperator.apply.flow)
    }
  }

  def filesInDirectoryMap(directory: String): Map[String, File] = {
    val fileArray = new File(directory).listFiles
      fileArray
      .map(_.getAbsolutePath)
      .map(_.replaceAll("""\\""", "/"))
      .map(_.reverse)
      .map(_.takeWhile(_ != '/'))
      .map(_.reverse)
      .zip(fileArray).toMap
  }

  private def emptyData() = Source.single {
    ByteString("Not Available")
  }
}
