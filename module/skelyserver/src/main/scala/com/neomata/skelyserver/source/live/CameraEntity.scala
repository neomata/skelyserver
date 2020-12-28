package com.neomata.skelyserver.source.live

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.HttpEntity.ChunkStreamPart
import akka.stream.{Attributes, Outlet, SourceShape}
import akka.stream.scaladsl.{BroadcastHub, Keep, Source}
import akka.stream.stage.{GraphStage, GraphStageLogic, OutHandler}
import com.neomata.skelyserver.source.VideoResourceEntity
import com.neomata.skelyserver.source.processing.operator.{ChunkOperator, FrameSerializer}
import org.bytedeco.opencv.opencv_core.Mat
import org.bytedeco.opencv.opencv_videoio.VideoCapture
import org.bytedeco.javacv.OpenCVFrameGrabber

class CameraEntity(device: Int)(implicit system: ActorSystem[_]) extends VideoResourceEntity {
  val fg = new OpenCVFrameGrabber(device)
  val capture = new VideoCapture(device)
  val graph = new FrameSource(capture, device)

  val source: Source[Mat, _] = Source.fromGraph(graph)

  val stream: Source[ChunkStreamPart, _] = {
    source.via(FrameSerializer.apply.flow)
      .via(ChunkOperator.apply.flow)
      .toMat(BroadcastHub.sink(1))(Keep.right).run()
  }

  def nextFrame: Source[ChunkStreamPart, _] = stream.take(1)
}

class FrameSource(capture: VideoCapture, device: Int) extends GraphStage[SourceShape[Mat]] {
  val outlet = Outlet.apply[Mat]("CameraSource")

  override def createLogic(inheritedAttributes: Attributes): GraphStageLogic = {
    new GraphStageLogic(shape) {
      val frame = new Mat
      setHandler(outlet, new OutHandler {
        override def onPull(): Unit = {
          capture.read(frame)
          push(outlet, frame)
        }
      })
    }
  }

  override def shape: SourceShape[Mat] = SourceShape.apply(outlet)
}
