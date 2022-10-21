package classActivities

object DropWhile extends App{
  var list=List(23,33,11,34,4,7,8,56734,55754)
  var result=list.filter(x=> {x%4 != 0 })

  var result1=list.find(x=> {x%4 != 0 })

  println(result)
  println(result1)

  var list2=List(List(1,2),List(3,4))
  var result3=list2.flatten
  println(result3)

  val result4=result3.fold(0)(_+_)
  println(result4)
  //0+1  =1 1+2  =3 3+3  6


  val result5=result3.foldLeft(0)(_+_)
  println(result5)

  val result6=result3.foldRight(0)(_+_)
  println(result6)


  val resul7=List(1,2,3,4,5,6,7)
  val (resul8,result9) = resul7.partition(x=> x%3 ==0)
  println(resul8)
  println(result9)


  val result25=resul7.reduce(_+_)
  println(result25)
}