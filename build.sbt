name := """play-scala-microservice"""
organization := "dope.nathan"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  guice,

  "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test,
  "org.reactivemongo" %% "play2-reactivemongo" % "0.16.5-play27",
  "io.swagger" %% "swagger-play2" % "1.7.1",
//  "org.webjars" %  "swagger-ui" % "3.2.2"
)

import play.sbt.routes.RoutesKeys

RoutesKeys.routesImport += "play.modules.reactivemongo.PathBindables._"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "dope.nathan.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "dope.nathan.binders._"
