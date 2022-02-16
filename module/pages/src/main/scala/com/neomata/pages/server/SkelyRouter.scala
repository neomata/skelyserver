package com.neomata.pages.server

import akka.http.scaladsl.model.RemoteAddress
import akka.http.scaladsl.server.Route

trait SkelyRouter {
  def route(remoteAddress: RemoteAddress): Route
}