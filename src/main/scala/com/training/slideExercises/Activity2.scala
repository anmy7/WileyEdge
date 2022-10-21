package com.training.slideExercises

object Activity2 extends App {
  println("Enter your name: ")
  var name = scala.io.StdIn.readLine()
  println("Enter your age: ")
  var age = scala.io.StdIn.readLine()
  println(s"The user $name has $age years old.")
}
