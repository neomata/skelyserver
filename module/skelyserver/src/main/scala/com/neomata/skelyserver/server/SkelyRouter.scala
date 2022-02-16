package com.neomata.skelyserver.server

import akka.http.scaladsl.model.RemoteAddress
import akka.http.scaladsl.server.Route

trait SkelyRouter {
  def route(remoteAddress: RemoteAddress): Route
}