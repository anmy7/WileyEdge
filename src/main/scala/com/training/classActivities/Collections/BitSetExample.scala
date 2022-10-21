package com.training.classActivities.Collections

import scala.collection.immutable.BitSet

object BitSetExample extends App {
  var bitSet: BitSet = BitSet(2, 4, 53, 6, 7, 8, 23, 323, 345, 34564, 435)
  bitSet.foreach(println)

  println("\nAfter adding 6666: ")
  bitSet += 6666
  bitSet.foreach(println)

  println(bitSet)
}
