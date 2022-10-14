package slideExercises

object ScalaCollections extends App{
  var map = Map("Alabama"->"Montgomery", "Alaska"-> "Juneau", "Arizona"->"Phoenix", "Arkansas"->"Little Rock")
  println("\nSTATES:")
  println("-"*50)
  map.keys.foreach((key)=>{
    println(key)
  })
  println("\nCAPITALS:")
  println("-" * 50)
  map.values.foreach((value) => {
    println(value)
  })
  println()
  for ((k,v)<- map){
    println(s"State: $k --> Capital: $v")
  }

}
