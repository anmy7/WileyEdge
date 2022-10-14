package slideExercises

object ScalaCollections10 extends App{
  var list = List(1,2,3,4,5,6,5,4,3,2,1)
  println((list.toList == list.reverse.toList))
}
