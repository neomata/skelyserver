package com.neomata.skelyserver.relay

import java.io.File
import java.nio.file.Path

object Access {
  trait Access

  case class Directory(path: String) extends Access {
    def apply(path: Path): Directory = Directory(path.toAbsolutePath.toString)
    def apply(path: File): Directory = Directory(path.getAbsolutePath)
  }

}

