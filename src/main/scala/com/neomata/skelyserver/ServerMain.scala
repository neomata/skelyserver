package com.neomata.skelyserver

import java.io.File

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.scaladsl.Sink
import com.neomata.skelyserver.relay.Access.Access
import com.neomata.skelyserver.relay.Basis
import com.neomata.skelyserver.server.Router
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.StrictLogging

import scala.util.Try

object ServerMain extends App with StrictLogging {
  val host = Try(args(1)).getOrElse("localhost")
  val port = Try(args(2).toInt).getOrElse(8080)

  implicit val system: ActorSystem[Access] = ActorSystem(Basis(), "server")
  val router = new Router(host, port, "")

  val bindingFuture = Http().newServerAt(host, port).connectionSource()

  val aftermath = bindingFuture.to(Sink.foreach { connection =>
    logger.info(s"Incoming connection from ${connection.remoteAddress} to server http://$host:$port/")

    connection.handleWithAsyncHandler(router.route)
  }).run()
}
