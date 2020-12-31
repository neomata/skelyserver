package com.neomata.skelyserver.relay

import java.io.File
import java.nio.file.Path

object Access {

  case class AccessibleDirectory(path: String) extends Access {
    def apply(path: Path): AccessibleDirectory = AccessibleDirectory(path.toAbsolutePath.toString)
    def apply(path: File): AccessibleDirectory = AccessibleDirectory(path.getAbsolutePath)
  }

}

trait Access


