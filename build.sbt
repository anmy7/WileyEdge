ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.9"

lazy val root = (project in file("."))
  .settings(
    name := "WileyEdge"
  )
val scalaLogging="com.typesafe.scala-logging" %% "scala-logging" % "3.9.5"
val utilControl="org.scala-sbt" %% "util-control" % "1.7.2"
val scalaTest="org.scalatest" %% "scalatest" % "3.2.14" % Test
val flatSpec="org.scalatest" %% "scalatest-flatspec" % "3.2.14" % Test
val funSpec="org.scalatest" %% "scalatest-funspec" % "3.2.14" % Test
val wordSpec="org.scalatest" %% "scalatest-wordspec" % "3.2.14" % Test

libraryDependencies ++= Seq(scalaLogging,utilControl,scalaTest)
// https://mvnrepository.com/artifact/com.typesafe.scala-logging/scala-logging-slf4j
//libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2"