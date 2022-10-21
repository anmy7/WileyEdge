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

val jodaTime="joda-time" % "joda-time" % "2.12.0"
val jodaConvert="org.joda" % "joda-convert" % "2.2.2"
val mysql = "mysql" % "mysql-connector-java" % "8.0.30"
val dep1="org.scalikejdbc" %% "scalikejdbc" % "3.5.0"
val dep2="com.h2database" % "h2" % "2.1.214" % Test
val dep3="ch.qos.logback" % "logback-classic" % "1.4.4" % Test
val AkkaVersion = "2.6.20"
val allakDependency="com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion
val akkaNewDep="com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test
val akkaStream="com.typesafe.akka" %% "akka-stream" % "2.6.20"

libraryDependencies ++= Seq(scalaLogging,utilControl,scalaTest, flatSpec, funSpec, wordSpec, jodaTime, jodaConvert, mysql, dep1, dep2, dep3, allakDependency, akkaNewDep, akkaStream)
//libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.30"
// https://mvnrepository.com/artifact/com.typesafe.scala-logging/scala-logging-slf4j
//libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2"