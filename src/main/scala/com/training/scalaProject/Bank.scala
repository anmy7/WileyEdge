package com.training.scalaProject

import java.sql._
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

  /*
  Add customer to the customers list
   */
  def addCustomer(customer: Customer): Unit ={
    customers += customer
  }
  /*
    Create a new customer
   */
  def createCustomer(firstname:String, middlename:String="", lastname:String, birthdate:String, phonenumber:String, address:String): Customer ={
    var customer = new Customer(this, firstname, middlename, lastname, birthdate, phonenumber, address)
    addCustomerToDatabase(customer.id, firstname, middlename, lastname, birthdate, phonenumber, address)
    customer
  }

  /*
    Add customer to the database
  */
  def addCustomerToDatabase(id:Int, firstname:String, middlename:String="", lastname:String, birthdate:String, phonenumber:String, address:String): Unit ={
    val url = "jdbc:mysql://localhost:3306/banking"
    val username = "root"
    val password = "root"
    // connection instance creation
    var connection: Connection = null
    try {
      Class.forName("com.mysql.jdbc.Driver")
      connection = DriverManager.getConnection(url, username, password)
      val insertMySQL = """INSERT INTO customers (id,firstName,middleName,lastName, birthDate, phoneNumber, address) values(?,?,?,?,?,?,?)"""
      val preparedStatement: PreparedStatement = connection.prepareStatement(insertMySQL)
      preparedStatement.setInt(1, id)
      preparedStatement.setString(2, firstname)
      preparedStatement.setString(3, middlename)
      preparedStatement.setString(4, lastname)
      preparedStatement.setString(5, birthdate)
      preparedStatement.setString(6, phonenumber)
      preparedStatement.setString(7, address)
      preparedStatement.execute()
    } catch {
      case e: Exception => e.printStackTrace()
    }
    finally {
      connection.close()
    }
  }

  /*
    Delete customer, accounts and credit cards
  */
  def deleteCustomer(customer: Customer): Unit = {
    customers -= customer
    getCustomerAccounts(customer).foreach((account:Account)=>{
      accounts -= account
      account.creditCards.foreach((cards)=>{
        creditCards -= cards
      })

    })
    customer.loans.foreach((loan) => {
      loans -= loan
    })
  }
  /*
  Get all customers
   */
  def getAllCustomers: ListBuffer[Customer] ={
    customers
  }
  /*
  Get all customers in String format
   */
  def getAllCustomersToString: ListBuffer[List[String]] ={
    var list = ListBuffer[List[String]]()
    customers.foreach((elements:Customer)=>{
      list += getInformationOfCustomer(elements)
    })
    list
  }

  /*
  Get the information of certain customer
   */
  def getInformationOfCustomer(customer: Customer):List[String]={
    customer.getPersonalInformation
  }

  /*
  Get customer by ID
   */
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

  /*
  Get customer by Full Name
   */
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

  /*
  Get customer by phone number.
   */
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

  /*
  Get the all the accounts of certain customer.
   */
  def getCustomerAccounts(customer: Customer): ListBuffer[Account] = {
    customer.accounts
  }

  /*
  Get and display the information of the Bank.
   */
  def getBankInformation: String ={
    var output = s"Bank name: $bankName\nSWIFT Code: $swiftCode\nNumber of Customers: ${customers.length}"
    println(output)
    output
  }

  /*
  Get the bank account by account number and sortcode
   */
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

  /*
  Change the conditions (length and/or interest) of a loan.
   */
  def changeConditionsLoan(loan:Loan, interest:Int = -1, lengthLoan:Int = -1): Unit ={
    if(interest != -1){
      loan.interest = interest
    }
    if(lengthLoan != -1){
      loan.lengthOfLoan = lengthLoan
    }
  }

  /*
  Accept a loan request
   */
  def acceptLoan(loan:Loan): Unit ={
    loan.acceptLoan
  }

  /*
  Block a bank account
   */
  def blockAccount(account:Account): Unit ={
    account.status = "Blocked"
  }

  /*
  Unblock a bank account
   */
  def unblockAccount(account: Account): Unit = {
    account.status = "Active"
  }

  /*
  Block a Credit Card
   */
  def blockCreditCard(card: CreditCard): Unit = {
    card.status = "Blocked"
  }

  /*
  Unblock a Credit Card
   */
  def unblockCreditCard(card: CreditCard): Unit = {
    card.status = "Active"
  }
}