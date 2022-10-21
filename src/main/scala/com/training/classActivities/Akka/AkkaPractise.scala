package com.training.classActivities.Akka

import akka.actor.typed.ActorSystem
import akka.actor.typed.javadsl.Behaviors
import akka.stream.scaladsl.{Flow, Sink, Source}

import scala.concurrent.{ExecutionContext, Future}

object AkkaPractise {
  implicit val system = ActorSystem(Behaviors.empty, "ScalaCohort")
  implicit val ec: ExecutionContext = system.executionContext

  var source = Source(1 to 100000)
  var flow = Flow[Int].map(x => x * 4)
  var sink = Sink.foreach[Int](x => println(x))

  val firstGraph = source.via(flow).to(sink)

  val asyncFlow = Flow[Int].mapAsync(4) {
    x =>
      Future {
        x * 2
      }
  }

  def main(args: Array[String]): Unit = {
    println("Welcome to akka world")
    firstGraph.run()
    source.via(asyncFlow).to(sink).run()
  }
}
