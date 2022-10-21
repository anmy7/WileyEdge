package scalaProject

import java.sql._
import scala.collection.mutable.ListBuffer

class Customer (Bank:Bank, firstname:String, middlename:String="", lastname:String, birthdate:String, phonenumber:String, address:String){
  var bank:Bank = Bank
  val id:Int = bank.getAllCustomers.length
  val firstName:String = firstname
  val middleName:String = middlename
  val lastName:String = lastname
  val birthDate:String = birthdate
  var phoneNumber:String = phonenumber
  var Address:String = address
  var digitalSignature: Int = 0
  var accounts:ListBuffer[Account] = ListBuffer()
  var accountForPhoneTransactions:Account = null
  var loans:ListBuffer[Loan] = ListBuffer()

  bank.addCustomer(this)

  /*
  Create a bank account
   */
  def createAccount(accounttype: String, Balance:Double=0): Account ={
    var account = new Account(this, bank, accounttype, Balance, "Active")
    addAccountToDatabase(account)
    account
  }

  /*
  Add the bank account to the database
   */
  def addAccountToDatabase(account: Account): Unit ={
    val url = "jdbc:mysql://localhost:3306/banking"
    val username = "root"
    val password = "root"
    // connection instance creation
    var connection: Connection = null
    try {
      Class.forName("com.mysql.jdbc.Driver")
      connection = DriverManager.getConnection(url, username, password)
      val insertMySQL = """INSERT INTO accounts (id,customerID,accountNumber,sortCode, accountType, balance, status) values(?,?,?,?,?,?,?)"""
      val preparedStatement: PreparedStatement = connection.prepareStatement(insertMySQL)
      preparedStatement.setInt(1, account.id)
      preparedStatement.setInt(2, id)
      preparedStatement.setInt(3, account.accountNumber)
      preparedStatement.setInt(4, account.sortCode)
      preparedStatement.setString(5, account.accountType)
      preparedStatement.setDouble(6, account.balance)
      preparedStatement.setString(7, account.status)
      preparedStatement.execute()
    } catch {
      case e: Exception => e.printStackTrace()
    }
    finally {
      connection.close()
    }
  }

  /*
  Delete a bank account
   */
  def deleteAccount(account:Account): Boolean ={
    if (accounts.contains(account)) {
      bank.accounts -= account
      accounts -= account
      true
    }
    else false
  }

  /*
  Get and display the personal information of the customer.
   */
  def getPersonalInformation: List[String] ={
    println("-"*60)
    println(s"Customer ID: $id")
    println(s"Name: $getFullName")
    println(s"Birth date: $birthDate")
    println(s"Phone Number: $phoneNumber")
    println(s"Address: $Address")
    println("-"*60)
    List(id.toString, getFullName, birthDate, phoneNumber, Address)
  }

  /*
  Get and display de finantial information of the customer
   */
  def getFinantialInformation:Unit={
    accounts.foreach(_.getAccountInformation())
    println()
    println("Loans: ")
    loans.foreach(_.displayLoanStatus())
  }

  /*
  Get the full name of the customer
   */
  def getFullName: String ={
    firstName + " " + middleName + " "+lastName
  }

  /*
  Set the digital signature
   */
  def setDigitalSignature(signature:Int): Unit ={
    if(signature.toString.length == 6){
      digitalSignature = signature
      println("Digital Signature configured successfully.")
    }else {
      println("ERROR setting the Sigital Signature. Please enter an Integer of 6 Digits.")
    }
  }

  /*
  Check if the digital signature is configured
   */
  def isDigitalSignatureSet: Boolean ={
    digitalSignature != 0
  }

  /*
  Check if the digital signature matches
   */
  def checkDigitalSignature(signature:Int): Boolean ={
    digitalSignature == signature
  }
}