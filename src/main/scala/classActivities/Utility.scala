package classActivities

import scala.util._

object Utility extends App{
  def methodWhichCanThrowException():String = "sjdfhkjshdfjkhfj"

  val handleExceptionorString=Try(methodWhichCanThrowException())

  val anotherStringProcess= handleExceptionorString match {
    case Success(value)=> s"i have optained a valid value:${value}"
    case Failure(exception)=> "I have obtained failure"

  }
  println(anotherStringProcess)


}