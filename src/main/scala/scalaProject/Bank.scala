package scalaProject

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._

class Bank (name:String, swiftcode:String){
  var bankName:String = name
  var swiftCode:String = swiftcode
  var customers:ListBuffer[Customer] = ListBuffer()
  var accounts:ListBuffer[Customer] = ListBuffer()
  var transactions:List[Map[String, Any]] = List()


  def addCustomer(customer:Customer): Unit ={
    customers += customer
  }

  def createCustomer(firstname:String, middlename:String="", lastname:String, birthdate:String, phonenumber:String, address:String): Customer ={
    var customer = new Customer(this, firstname, middlename, lastname, birthdate, phonenumber, address)
    customer
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