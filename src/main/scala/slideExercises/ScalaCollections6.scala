package slideExercises

object ScalaCollections6 extends App{
  def checkNumbers(array: Array[Int]): Boolean ={
    array.contains(4) || array.contains(7)
  }
  var array = Array(2,4,5,7,9)
  println(checkNumbers(array))
  var array2 = Array(2, 5,5,6,9)
  println(checkNumbers(array2))
  var array3 = Array(2,5,7,6,9)
  println(checkNumbers(array3))
}
