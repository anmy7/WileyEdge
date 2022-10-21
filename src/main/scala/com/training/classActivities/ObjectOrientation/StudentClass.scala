package com.training.classActivities.ObjectOrientation

class StudentClass {
  var name = ""
  var id = 0

  def setName(name:String): Unit ={
    this.name = name
  }

  def IncreaseID(): Unit ={
    id += 1
  }
}
