package com.training.classActivities.Collections

object StreamExamples extends App {
  val stream = 1 #:: 2 #:: 8 #:: 20 #:: 40 #:: Stream.empty
  println(stream)

  println("Take first 2 numbers from stream")

  stream.take(2).print()
  println()
  stream.take(5).print()
  println()
  println(stream.max)
}
