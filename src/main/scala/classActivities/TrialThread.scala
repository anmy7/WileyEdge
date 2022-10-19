package classActivities

object Foo{
  private var ctr=0L
  def getCtr=this.synchronized{
    ctr=ctr+1
    ctr
    println("in block"+Thread.currentThread().getName)
  }
  def bar={
    val currCt=getCtr
    currCt
  }
}
class TrialThread extends Thread{
  override  def run(): Unit ={
    this.synchronized {
      println(s"Following thread is executing the code ${Foo.bar} and ${Thread.currentThread().getName}")
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