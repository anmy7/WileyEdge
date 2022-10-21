package com.training.classActivities.utilities

import scala.util.{Failure, Success, Try}

object Utility extends App {
  def methodWhichCanThrowException(): String = "sjdfhkjshdfjkhfj"

  val handleExceptionorString = Try(methodWhichCanThrowException())

  val anotherStringProcess = handleExceptionorString match {
    case Success(value) => s"i have optained a valid value:${value}"
    case Failure(exception) => "I have obtained failure"

  }
  println(anotherStringProcess)


}
