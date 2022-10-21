package com.training.classActivities.Collections

object vectorplay extends App{
  var vector:Vector[Int]=Vector(5,2,6,7,9,1,4,7)

  var vector2=Vector(2.4,7,1,7)
  var vector3: Vector[String] = Vector.empty
  println(vector)
  println(vector2)
  println(vector3)
  //append an element
  vector3 = vector3 :+ "Racing"
  vector3 = vector3 :+ "temp"
  println(vector3)
  var mergeVector = vector2.concat(vector3)
  println(mergeVector)
  println(mergeVector.reverse)
  var fourthvector=vector2.sorted
  val from1to5 = 1 to 5
  println(s"$from1to5")
  val list1to100 = (1 to 100).foreach(println)
  from1to5.foreach(print(_))
}