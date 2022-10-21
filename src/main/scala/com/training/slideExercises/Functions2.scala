package com.training.slideExercises

object Functions2 extends App{
  case class Student(name:String, age:Int)

  var student1:Student = Student("John", 7)
  var student2:Student = Student("Jack", 13)
  var student3:Student = Student("Joe", 15)
  var student4:Student = Student("Jill", 15)
  var student5:Student = Student("James", 11)

  var listStudents:List[Student] = List(student1, student2, student3, student4, student5)

  def student15True(students:List[Student], count:Int = 0): Boolean ={
    println(students(count))
    if (students(count).age == 15){
      true
    } else{
      if (count < 4) student15True(students, count+1) else false
    }
  }
  print(student15True(listStudents))
}
