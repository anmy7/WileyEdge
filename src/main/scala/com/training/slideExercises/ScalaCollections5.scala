package com.training.slideExercises

object ScalaCollections5 extends App{
  def sumArray(): Int ={
    var array = Array[Int](1, 2, 3, 4, 5, 7, 9, 11, 14, 12, 16)
    if(array.isEmpty){
      0
    } else if (array.length < 3){
      array.sum
    } else{
      array.dropRight(3).sum
    }
  }

  println(sumArray())
}
