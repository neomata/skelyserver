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

//  val route: Route = path(Segment) { name => innerRoute(name) }
  val route: Route = {
    concat(
      pathSingleSlash {
        complete(HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`, HtmlCodes.mainPage)))
      },
      path("submit") {
        parameters("q1", "q2", "q3", "q4", "q5", "q6", "q7", "q8", "q9", "q10") { (q1, q2, q3, q4, q5, q6, q7, q8, q9, q10) =>

        }
      }
    )
  }
}
