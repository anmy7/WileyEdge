package scalaProject

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._

class Bank (name:String, swiftcode:String){
  var bankName:String = name
  var swiftCode:String = swiftcode
  var customers:ListBuffer[Customer] = ListBuffer()
  var accounts:ListBuffer[Account] = ListBuffer()
  var loans:ListBuffer[Loan] = ListBuffer()
  var creditCards:ListBuffer[CreditCard] = ListBuffer()
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
    getCustomerAccounts(customer).foreach((account:Account)=>{
      accounts -= account
    })
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
        if (elements.id == ID) {
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
        if (elements.getFullName == fullName) {
          output = elements
          break
        }
      })
    }
    output
  }

  def getCustomerByPhoneNumber(phoneNumber: String): Customer = {
    var output: Customer = null
    breakable {
      customers.foreach((elements: Customer) => {
        if (elements.phoneNumber == phoneNumber) {
          output = elements
          break
        }
      })
    }
    output
  }

  def getCustomerAccounts(customer: Customer): ListBuffer[Account] = {
    customer.accounts
  }

  def getBankInformation: String ={
    var output = s"Bank name: $bankName\nSWIFT Code: $swiftCode\nNumber of Customers: ${customers.length}"
    println(output)
    output
  }

  def getAccountByAccNumber(accNumber:Int, sortc:Int): Account ={
    var output:Account = null
    breakable {
      accounts.foreach((account: Account) => {
        if (accNumber == account.accountNumber && sortc == account.sortCode) {
          output = account
          break
        }
        else null
      })
    }
    output
  }

  def changeConditionsLoan(loan:Loan, interest:Int = -1, lengthLoan:Int = -1): Unit ={
    if(interest != -1){
      loan.interest = interest
    }
    if(lengthLoan != -1){
      loan.lengthOfLoan = lengthLoan
    }
  }
  def acceptLoan(loan:Loan): Unit ={
    loan.acceptLoan
  }

  def blockAccount(account:Account): Unit ={
    account.status = "Blocked"
  }

  def unblockAccount(account: Account): Unit = {
    account.status = "Active"
  }

  def blockCreditCard(card: CreditCard): Unit = {
    card.status = "Blocked"
  }

  def unblockCreditCard(card: CreditCard): Unit = {
    card.status = "Active"
  }
}