package scalaProject

class Customer (ID:Int, firstname:String, middlename:String="", lastname:String, birthdate:String, phonenumber:String, address:String){
  val id = ID
  val firstName:String = firstname
  val middleName:String = middlename
  val lastName:String = lastname
  val birthDate:String = birthdate
  var phoneNumber:String = phonenumber
  var Address:String = address
//  var accounts:Account
//  var loans:Loan

  def getPersonalInformation: List[String] ={
//    println(s"Customer ID: $id")
//    println(s"Name: $getFullName")
//    println(s"Birth date: $birthDate")
//    println(s"Phone Number: $phoneNumber")
//    println(s"Address: $Address")
    List(id.toString, getFullName, birthDate, phoneNumber, Address)
  }


  def getFinantialInformation:Unit={
    println("Accounts: ")
    println("Loans: ")
  }

  def getFullName: String ={
    firstName + " " + middleName + " "+lastName
  }
}

object Customer extends App{
  var nico = new Customer(1,"Nicolas", "Andres", "Mesa Yip", "16/04/2000", "07776228621", "6 Oaten Hill")
  nico.getPersonalInformation
}