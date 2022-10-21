package com.training.slideExercises

object ControlStructures4 extends App{
  def productOfUnicode(text: String): Long = {
    var result: Long = 1
    for (i <- 0 until text.length) {
      result *= text.codePointAt(i)
      println(result)
    }
    result
  }
  productOfUnicode("Hello")
}
