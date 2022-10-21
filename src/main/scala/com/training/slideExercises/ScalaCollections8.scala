package com.training.slideExercises

object ScalaCollections8 extends App{
  var array = Array(20,12,23,17,7,8,10,2,1)
  println(Array.concat(array.filter(_%2==0), array.filter(_%2!=0)).toList)
}
