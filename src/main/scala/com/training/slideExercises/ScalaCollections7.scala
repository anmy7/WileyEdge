package com.training.slideExercises

object ScalaCollections7 extends App {
  def commonElements(array: Array[Int], array2:Array[Int]):List[Int]={
    var a = List[Int]()
    array.foreach((elements:Int)=> {
      if(array2.contains(elements)){
        a = a :+ elements
      }
    })
    a
  }
  var array = Array(2,4,5,7,9)
  var array2 = Array(2,3,5,6,9)
  commonElements(array, array2)
}
