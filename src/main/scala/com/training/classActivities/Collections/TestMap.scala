package com.training.classActivities.Collections

object TestMap extends App{
  val s1=Set(5,1,3,5,6,9)
  val result=s1.map(x=> x*x)
  println(result)
  val s2=List("jiensh","aasd","asdasd","asdasdasd","asdasdasdad")
  val result2=s2.map(_.toUpperCase())
  println(result2)
  val s22=List("jiensh","aasd","asdasd","asdasdasd","asdasdasdad")
  val result22=s2.flatMap(_.toUpperCase())
  println(result22)}
