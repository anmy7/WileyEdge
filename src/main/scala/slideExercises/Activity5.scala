package slideExercises

object Activity5 extends App {

  import scala.io.StdIn.readLine

  println("Enter your first name: ")
  var fname = readLine()
  println("Enter your last name: ")
  var lname = readLine()
  println("Enter your favourite movie: ")
  var movie = readLine()

  println(s"First Name: $fname\nLast Name: $lname\nFavourite Movie: $movie")
}
