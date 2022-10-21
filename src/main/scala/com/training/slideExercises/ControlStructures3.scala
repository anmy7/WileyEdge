package com.training.slideExercises

object ControlStructures3 {
  def countdown(number:Int): Unit ={
    for(x <- number to 0 by -1) println(x)
  }
}
