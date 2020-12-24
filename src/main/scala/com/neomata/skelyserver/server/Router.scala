package com.neomata.skelyserver.server

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.HttpEntity.{ChunkStreamPart, Chunked}
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, MediaTypes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.neomata.skelyserver.http.html.HtmlCodes
import com.typesafe.scalalogging.StrictLogging

class Router(host: String, port: Int, var directory: String)(implicit system: ActorSystem[_]) extends StrictLogging {
  val rh = new ResourceHandler(directory)

  def updateDirectory(path: String): Unit = {
    directory = path
    rh.directory = path
  }

  def innerRoute(path: String): Route = {
    concat(
      path match {
        case file => complete {
          HttpResponse(entity = HttpEntity.Chunked(MediaTypes.`application/octet-stream`, rh.octetStream(file)))
        }
      },
      get(complete("File not found"))
    )
  }

  val route: Route = path(Segment) { name => innerRoute(name) }
}
