package scalaProject

import java.util.Calendar
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
//  var loans:Loan

  bank.addCustomer(this)


  def createAccount(accounttype: String, Balance:Double=0): Account ={
    var account = new Account(this, bank, accounttype, Balance, "Active")
    account
  }

  def deleteAccount(account:Account): Boolean ={
    if (accounts.contains(account)) {
      bank.accounts -= account
      accounts -= account
      true
    }
    else false
  }

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


  def getFinantialInformation:Unit={
    println(accounts.foreach(_.getAccountInformation()))

    println("Loans: ")
  }

  def getFullName: String ={
    firstName + " " + middleName + " "+lastName
  }

  def setDigitalSignature(signature:Int): Unit ={
    if(signature.toString.length == 6){
      digitalSignature = signature
      println("Digital Signature configured successfully.")
    }else {
      println("ERROR setting the Sigital Signature. Please enter an Integer of 6 Digits.")
    }
  }

  def isDigitalSignatureSet: Boolean ={
    digitalSignature != 0
  }

  def checkDigitalSignature(signature:Int): Boolean ={
    digitalSignature == signature
  }
}