package com.neomata.skelyserver.server

import java.io.File
import java.nio.file.Paths

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.HttpEntity.ChunkStreamPart
import akka.stream.IOResult
import akka.stream.scaladsl.{FileIO, Source}
import akka.util.ByteString
import com.neomata.skelyserver.source.live.CameraEntity

import scala.concurrent.Future

object ResourceHandler {
  case class PostMarshal(bytes: ByteString)
}

class ResourceHandler(resourceHome: String)(implicit system: ActorSystem[_]) {
  protected[this] val videoDirectoryPath: String = s"$resourceHome/videos/"
  val videoDirectoryFile: File = new File(videoDirectoryPath)


  var ccMap: Map[Int, CameraEntity] = cueAllCameras()

  def cameraStream(device: Int): Source[ChunkStreamPart, _] = {
    ccMap.get(device) match {
      case Some(camera) => camera.stream
      case None =>
        if (potentialCamera(device)) {
          ccMap(device).stream
        } else {
          emptyData()
        }
    }
  }

  def cameraFrame(device: Int): Source[ChunkStreamPart, _] = {
    ccMap.get(device) match {
      case Some(camera) => camera.nextFrame
      case None =>
        if (potentialCamera(device)) {
          ccMap(device).nextFrame
        } else {
          emptyData()
        }
    }
  }


//  def octetStream(fileName: String): Source[ByteString, Future[IOResult]] = {
//    val fullName = paths(fileName)
//    val file = new File(s"$videoDirectoryPath/$fullName")
//
//    FileIO.fromPath(Paths.get(file.getAbsolutePath))
//  }

  private def cueAllCameras(deviceIndex: Int = 0): Map[Int, CameraEntity] = {
    val cc = new CameraEntity(deviceIndex)
    if (cc.capture.isOpened) {
      cueAllCameras(deviceIndex + 1) + (deviceIndex -> cc)
    } else {
      Nil.toMap
    }
  }

  private def potentialCamera(device: Int): Boolean = {
    val cc = new CameraEntity(device)
    val canAccess = cc.capture.isOpened
    if (canAccess) {
      ccMap = ccMap + (device -> cc)
    }
    canAccess
  }


  private def emptyData(): Source[ChunkStreamPart, _] = Source.single {
    ChunkStreamPart(ByteString("Not Available"))
  }
}
