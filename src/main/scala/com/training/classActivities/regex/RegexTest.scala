package com.training.classActivities.regex

object RegexTest extends App{
  val str ="all".r
  val source ="all hello to all from these world"
  println(str.findFirstIn(source))
  str.findAllMatchIn(source).foreach(x=>println(x))
  val needChange="repalcetest"
  val finalStr="replacetest".replaceAll(".test","**")
  println(needChange)
  println(finalStr)
//anyhthing which ends with k
  val strcheck="check"
  val finaltempstr=strcheck.matches(".*k")
  println(finaltempstr)
  val sstr="something to test the result"
  var sfinalStr=sstr.split(".ng",2)
  for(sq<- sfinalStr){
    println( s"here are teh array ${sq}")
  }
}
