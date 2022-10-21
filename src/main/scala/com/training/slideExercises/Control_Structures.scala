package com.training.slideExercises

object Control_Structures extends App {
  def signum(number: Int): Int = {
    if (number > 0) {
      return 1
    }
    else if (number < 0) {
      return -1
    }
    else {
      return 0
    }
  }

  def forloop(n: Int): Unit = {
    for (i <- n to 0 by -1) {
      println(i)
    }
  }

  def productOfUnicode(text: String): Long = {
    var result: Long = 1
    for (i <- 0 until text.length) {
      result *= text.codePointAt(i)
      println(result)
    }
    result
  }

  //  print(signum(-345))
  //  forloop(45)
  productOfUnicode("Hello")
}
