package com.training.slideExercises

import scala.io.StdIn._

object ScalaCollections2 extends App{
  def print(string:String): Unit ={
    println(string)
  }

  def readString(message:String): String ={
    println(message)
    var read = readLine()
    read
  }

  def readInts(message: String): Int = {
    println(message)
    var read = readInt()
    read
  }


  def readIntRanged(message:String, minval:Int, maxval:Int): Int ={
    var read:Int = 0
    do{
      println(message)
      read = readInt()
    } while(read < minval || read > maxval)
    read
  }


  def readDoubles(message: String): Double = {
    println(message)
    var read = readDouble()
    read
  }


  def readDoubleRanged(message: String, minval: Double, maxval: Double): Double = {
    var read: Double = 0
    do {
      println(message)
      read = readDouble()
    } while (read < minval || read > maxval)
    read
  }


  def readFloats(message: String): Float = {
    println(message)
    var read = readFloat()
    read
  }


  def readFloatRanged(message: String, minval: Float, maxval: Float): Float = {
    var read: Float = 0
    do {
      println(message)
      read = readFloat()
    } while (read < minval || read > maxval)
    read
  }


  def readLongs(message: String): Long = {
    println(message)
    var read = readLong()
    read
  }


  def readLongRanged(message: String, minval: Long, maxval: Long): Long = {
    var read: Long = 0
    do {
      println(message)
      read = readLong()
    } while (read < minval || read > maxval)
    read
  }
}
