package com.neomata.skelyserver.server

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.HttpEntity.{ChunkStreamPart, Chunked}
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, MediaTypes, RemoteAddress}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.typesafe.scalalogging.StrictLogging

trait SkelyRouter {
  def route(remoteAddress: RemoteAddress): Route
}

class Router(host: String, port: Int, var directory: String)(implicit system: ActorSystem[_]) extends SkelyRouter with StrictLogging {
  val rh = new ResourceHandler(directory)
  var submissions = 20

  def updateDirectory(path: String): Unit = {
    directory = path
    rh.directory = path
  }

  def innerRoute(path: String, remoteAddress: RemoteAddress): Route = {
    concat(
      path match {
        case file => complete {
          HttpResponse(entity = HttpEntity.Chunked(MediaTypes.`application/octet-stream`, rh.fetchFile(file, remoteAddress)))
        }
      },
      get(complete("File not found"))
    )
  }

  override def route(remoteAddress: RemoteAddress): Route = path(Segment) { name =>
    innerRoute(name, remoteAddress)
  }
}
