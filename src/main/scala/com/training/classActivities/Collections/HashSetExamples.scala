package com.training.classActivities.Collections

import scala.collection.immutable.HashSet

object HashSetExamples extends App {
  var hashSet: HashSet[String] = HashSet("3Pilaka Ruchitha", "4Sugandha Dhanawade", "6Irfan Khan Patan", "7Vaibhav Kemani", "8Sumukkh Jadhav")


  var list = hashSet.toList
  println(list)

  var head = hashSet.head
  println(head)

  println(hashSet.isEmpty)

  hashSet.foreach((element: String) => {
    println(element)
  })
}
