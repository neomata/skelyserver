val akkaVersion = "2.6.10"
val akkaHttpVersion = "10.2.2"

mainClass in (Compile, run) := Some("com.neomata.skelyserver.ServerMain")

libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-stream-typed" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
libraryDependencies += "org.bytedeco" % "javacv-platform" % "1.5.3"