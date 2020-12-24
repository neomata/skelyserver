package com.neomata.skelyserver.server

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.HttpEntity.{ChunkStreamPart, Chunked}
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, MediaTypes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.neomata.skelyserver.http.html.HtmlCodes
import com.typesafe.scalalogging.StrictLogging

class Router(host: String, port: Int, var resourceHome: String)(implicit system: ActorSystem[_]) extends StrictLogging {
  val rh = new ResourceHandler(resourceHome)

  def updateDirectory(path: String): Unit = {
    resourceHome = path
    rh.resourceHome = path
  }

  def snapshotRoute: Route = get {
    parameters("device") { device =>
      complete {
        HttpResponse(entity = Chunked(
          MediaTypes.`image/jpeg`, rh.cameraFrame(device.toInt))
        )
      }
    }
  }

  def cameraRoute: Route = get {
    parameters("device", "browser") { (device, browser) =>
      if (!browser.toBoolean) {
        complete(HttpResponse(entity = Chunked(
          MediaTypes.`application/octet-stream`, rh.cameraStream(device.toInt))))
      } else {
        complete(HttpResponse(entity = HttpEntity(
          ContentTypes.`text/html(UTF-8)`, HtmlCodes.cameraHtmlScript(host, port, device.toInt)))
        )
      }
    }
  }


  def innerRoute(path: String): Route = {
    concat(
      path match {
        case snapshot if snapshot == "snapshot" => snapshotRoute
        case camera if camera == "camera" => cameraRoute
      },
      get(complete("File not found"))
    )
  }

  val route: Route = path(Segment) { name => innerRoute(name) }

}
