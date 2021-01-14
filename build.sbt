ThisBuild / organization := "com.neomata"

ThisBuild / scalaVersion := "2.13.4"

ThisBuild / version := "0.1"


lazy val skelyserver = project.in(file("module/skelyserver"))
  .settings(
    name := "skelyserver"
  )

lazy val pages = project.in(file("module/pages"))
  .aggregate(skelyserver)
  .dependsOn(skelyserver)
  .settings(
    name := "pages"
  )
