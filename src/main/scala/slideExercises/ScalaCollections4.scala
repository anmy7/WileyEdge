package slideExercises

object ScalaCollections4 extends App {
  var array = Array("Red", "Blue", "Black", "Green", "White")
  array(0) = ""
  println(array.toList)
}
