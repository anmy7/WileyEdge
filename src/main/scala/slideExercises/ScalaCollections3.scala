package slideExercises

object ScalaCollections3 extends App{
  var array = Array(1.2, 1.7, 1.12, 1.16, 1.81, 1.99)
  var result1:Double = array.sum
  var result2:Double = 0
  for(x <- array){
    result2 += x
  }

  println(result1)
  println(result2)
}
