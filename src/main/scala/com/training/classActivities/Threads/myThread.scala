package com.training.classActivities.Threads

class myThread extends Thread{
  override  def run(): Unit ={
    if(Thread.currentThread().isDaemon){
      println("daemon thread")
    }else{
      println("other work")
    }
  }
}
object testthreadutility extends App{
  var a= new myThread()
  var b= new myThread()
  var c= new myThread()
  a.setDaemon(true)
  b.start()
  c.start()
  a.start()
}