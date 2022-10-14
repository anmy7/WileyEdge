package scalaProject

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._

class Bank (name:String, swiftcode:String){
  var bankName = name
  var swiftCode = swiftcode
  var customers:ListBuffer[Customer] = ListBuffer()

  def addCustomer(customer:Customer): Unit ={
    customers += customer
  }

  def deleteCustomer(customer: Customer): Unit = {
    customers -= customer
  }

  def getAllCustomers: ListBuffer[Customer] ={
    customers
  }

  def getAllCustomersToString: ListBuffer[List[String]] ={
    var list = ListBuffer[List[String]]()
    customers.foreach((elements:Customer)=>{
      list += getInformationOfCustomer(elements)
    })
    list
  }

  def getInformationOfCustomer(customer: Customer):List[String]={
    customer.getPersonalInformation
  }

  def getCustomerByID(ID:Int): Customer ={
    var output:Customer = null
    breakable {
      customers.foreach((elements: Customer) => {
        if (elements.getPersonalInformation.head.toInt == ID) {
          output = elements
          break
        }
      })
    }
    output
  }

  def getCustomerByName(fullName:String): Customer ={
    var output: Customer = null
    breakable {
      customers.foreach((elements: Customer) => {
        if (elements.getPersonalInformation(1) == fullName) {
          output = elements
          break
        }
      })
    }
    output
  }

  def getBankInformation: String ={
    var output = s"Bank name: $bankName\nSWIFT Code: $swiftCode\nNumber of Customers: ${customers.length}"
    println(output)
    output
  }
}

object Bank extends App(){
  var santander = new Bank("Santander Bank", "SWEDREF")
  var nico = new Customer(1,"Nicolas", "Andres", "Mesa Yip", "16/04/2000", "07776228621", "6 Oaten Hill")
  var p = new Customer(2,"Nicolassss", "Andres", "Mesa Yip", "16/04/2000", "07776228621", "6 Oaten Hill")
  santander.addCustomer(nico)
  santander.addCustomer(p)
  println(santander.getAllCustomersToString)
  println(santander.getInformationOfCustomer(santander.getCustomerByName("Nicolas Andres Mesa Yip")))
  santander.getBankInformation
}