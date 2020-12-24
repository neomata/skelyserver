package com.neomata.skelyserver.relay

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import com.neomata.skelyserver.relay.Access.Access
import com.neomata.skelyserver.server.Router

object Basis {
  def apply(): Behavior[Access] = Behaviors.receive { (context, message) =>
    message match {
      case Access.Directory(path) =>
        Behaviors.same
    }
  }
}
