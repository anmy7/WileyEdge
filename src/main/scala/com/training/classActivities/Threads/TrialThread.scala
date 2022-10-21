package com.training.classActivities.Threads

import org.joda.time.DateTime


object Foo{
  private var ctr=0L
  def getCtr=this.synchronized{
    ctr=ctr+1
    ctr
    var dt = new DateTime();
    println("in block"+Thread.currentThread().getName + s"-----> ${dt}")
  }
  def bar={
    val currCt=getCtr
    currCt
  }
}
class TrialThread extends Thread{
  var dt = new DateTime();
  override  def run(): Unit ={
    this.synchronized {
      println(s"Following thread is executing the code ${Foo.bar} and ${Thread.currentThread().getName} -----> ${dt}")
    }
  }
}
object Demotrial extends App{
  var t1=new TrialThread()
  t1.start()

  var t2=new TrialThread()
  t2.start()

  var t3=new TrialThread()
  t3.start()
  var t4=new TrialThread()
  t4.start()
}