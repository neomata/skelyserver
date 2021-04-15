package com.neomata.pages

import akka.actor.typed.{ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.RemoteAddress
import akka.stream.scaladsl.Sink

import com.neomata.pages.server.Router
import com.typesafe.scalalogging.StrictLogging

import scala.util.Try

object Basis { def apply(): Behavior[Any] = Behaviors.setup(_ => Behaviors.same[Any])}

object ServerMain extends App with StrictLogging {
  val host = Try(args(0)).getOrElse("localhost")
  val port = Try(args(1).toInt).getOrElse(8080)
  logger.info("Hosting at {}:{}", host, port)

  implicit val system: ActorSystem[_] = ActorSystem(Basis(), "server")
  val router = new Router(host, port)

  val bindingFuture = Http(system).newServerAt(host, port).connectionSource()

  val aftermath = bindingFuture.to(Sink.foreach { connection =>
    logger.info(s"Incoming connection from ${connection.remoteAddress} to server http://$host:$port/")

    connection.handleWithAsyncHandler(router.route(RemoteAddress(connection.remoteAddress)))
  }).run()
}
