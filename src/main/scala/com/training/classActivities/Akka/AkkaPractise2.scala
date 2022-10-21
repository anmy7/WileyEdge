package com.training.classActivities.Akka

import akka.actor.typed.ActorSystem
import akka.actor.typed.javadsl.Behaviors
import akka.stream.scaladsl.{Flow, Sink, Source}

import scala.concurrent.{ExecutionContext, Future}

object AkkaPractise2 {
  implicit val system = ActorSystem(Behaviors.empty, "ScalaCohort")
  implicit val ec: ExecutionContext = system.executionContext
  val source = Source(1 to 100000)
  val flow = Flow[Int].map(x => x * 4)
  val sink = Sink.foreach[Int](x => println(x))
  val aysncFlow = Flow[Int].mapAsync(6) {
    x =>
      Future {
        x * 6
      }
  }
  val fastSource = Source(1 to 10000).via(aysncFlow).map {
    x =>
      println(s"waiting on comsuer ${x}")
      x
  }.async
  val slowSink = Sink.foreach[Int] {
    x =>
      Thread.sleep(1000)
      println(x)
  }

  def main(args: Array[String]): Unit = {
    fastSource.to(slowSink).run()
  }
}
