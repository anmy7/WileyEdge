package com.training.classActivities.utilities

object Extractors {
  def main(args: Array[String]): Unit = {
    println(apply("jinesh","gmail.com"))
    println(unApply(apply("jinesh","gmail.com")))
    println(unApply("jiensh ranawat"))
  }
  def apply(user:String,domain: String): String ={
    user +"@"+domain
  }
  def unApply(str:String):Option[(String,String)]={
    val parts=str.split("@")
    if(parts.length == 2){
      Some(parts(0),parts(1))
    }else{
      None
    }
  }
}
