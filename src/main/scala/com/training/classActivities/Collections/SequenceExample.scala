package com.training.classActivities.Collections

object SequenceExample extends App {
  var sequence: Seq[Int] = Seq(4, 3, 345, 45, 12, 77, 994, 32346)
  sequence.foreach(println)

  println(sequence.head)
  println(sequence.tail)
  println(sequence.length)
  println(sequence.dropWhile(_ > 100))
  println(sequence.filterNot(_ > 100))
  println(sequence.indexWhere(_ == 77))
  println(sequence.reverse)
  println(sequence.splitAt(4))
}
