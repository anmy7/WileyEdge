package com.training.classActivities

import scala.util.control.Breaks._

class StudentTemp(roll:Int){
  var rollNumber=roll

}

class PeronalDetails(NameV:String,ageT:Int){
  var Name=NameV
  var age=ageT


}
object MatchNumbers extends  App{

  var st=new StudentTemp(1)
  var pt=new PeronalDetails("Jinesh",35)
  val studentRegster:Map[StudentTemp,PeronalDetails]= Map(st->pt)

  printingStatus(getStudentName(studentRegster,1))

  def getStudentName(studentRegster:Map[StudentTemp,PeronalDetails],roll:Int):Option[PeronalDetails]={
    var studentKeys=studentRegster.keys
    var check=false
    var student:Option[PeronalDetails] = None
    for(a <- studentKeys){
      if(a.rollNumber.equals(roll)){
        check=true
        student = studentRegster.get(a)
        break
      }
    }
    student
  }
  def printingStatus(student:Option[PeronalDetails]):Unit={
    student match {
      case student => println("One"+student)

      case None => println("No")
    }
  }
}