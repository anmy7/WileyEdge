package slideExercises

object ControlStructures1 extends App{
  def signum(number: Int):Int={
    if(number < 0) -1
    else if(number == 0) 0
    else 1
  }
  print(signum(0))
}
