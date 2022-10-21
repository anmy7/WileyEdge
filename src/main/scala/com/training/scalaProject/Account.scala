package com.training.scalaProject

import java.sql.{Connection, DriverManager, PreparedStatement}
import java.util.Calendar
import scala.collection.mutable.ListBuffer
import scala.util.Random
import scala.util.control.Breaks._

class Account(Customer:Customer, Bank:Bank, accounttype: String, Balance:Double, Status: String) {
  var bank:Bank = Bank
  var id:Int = bank.accounts.length
  var accountType:String = accounttype
  var balance:Double = Balance
//  status["Active, Frozen, Blocked"]
  var status:String = Status
  var customer:Customer = Customer
  var transactions:List[Map[String, Any]] = List()
  bank.accounts += this
  customer.accounts += this
  var accountNumber:Int =0
  var sortCode:Int=0
  var creditCards:ListBuffer[CreditCard] = ListBuffer[CreditCard]()
  generateAccountNumber()
//  setAccountNumber(11111111, 222222)

  /*
  Create a credit card
   */
  def createCreditCard(cardtype:String, cashierLimit:Double, purchaselimit:Double, Balance:Double = 0): CreditCard ={
    var balanceToAdd:Double = 0
    if(cardtype == "Prepaid"){
      if(Balance <= this.checkBalance){
        balanceToAdd = Balance
      } else balanceToAdd = 0
    } else balanceToAdd = 0
    var card:CreditCard = new CreditCard(this, cardtype, cashierLimit, purchaselimit, balanceToAdd)
    addCreditCardToDatabase(card)
    card
  }

  /*
  Generate the bank account number and sortcode
   */
  def generateAccountNumber(): Unit ={
    if(accountNumber == 0 && sortCode == 0) {
      var unique: Boolean = true
      var accNumber: Int = 0
      var sortcode: Int = 0
      while (unique) {
        accNumber = Random.nextInt(89999999) + 10000000
        sortcode = Random.nextInt(899999) + 100000

        breakable {
          bank.accounts.foreach((account: Account) => {
            if (sortcode >= 100000 && sortcode < 1000000 && accNumber >= 10000000 && accNumber < 100000000) {
              if (account.sortCode == sortcode || account.accountNumber == accNumber) {
                unique = true
                break
              } else unique = false
            }
          })
        }
      }
      accountNumber = accNumber
      sortCode = sortcode
    }
  }

  /*
  Set and specific account number and sortcode (used for testing)
   */
  def setAccountNumber(acc: Int, sort: Int): Unit = {
    accountNumber = acc
    sortCode = sort
  }

  /*
  Check balance of the account
   */
  def checkBalance: Double ={
    balance
  }

  /*
  Increase balance of the account
   */
  def increaseBalance(amount:Double): Unit ={
    balance += amount
  }

  /*
  Decrease balance of the account
   */
  def decreaseBalance(amount: Double): Unit = {
    balance -= amount
  }

  /*
  Do a transaction to an account
   */
  def doTransaction(destinatary: Account, amount:Double, concept:String, digitalSignature:Int): Unit ={
    if(status == "Active"){
      if(customer.isDigitalSignatureSet){
        if(amount <= balance){
          if(customer.checkDigitalSignature(digitalSignature)){
            if(destinatary.status == "Active"){
              val now = Calendar.getInstance()
              decreaseBalance(amount)
              destinatary.increaseBalance(amount)
              transactions = transactions :+ Map("id" -> bank.transactions.length, "sender" -> this, "destinatary" -> destinatary, "amount" -> amount, "concept" -> concept, "date" -> now.getTime)
              destinatary.transactions = destinatary.transactions :+ Map("id" -> bank.transactions.length, "sender" -> this, "destinatary" -> destinatary, "amount" -> ("+"+amount.toString), "concept" -> concept, "date" -> now.getTime)
              bank.transactions = bank.transactions :+ Map("id" -> bank.transactions.length, "sender" -> this, "destinatary" -> destinatary, "amount" -> amount, "concept" -> concept, "date" -> now.getTime)
            } else println("The destinatary account is either Frozen or Blocked")
          } else println("Wrong Digital Signature")
        } else println("Insufficient balance")
      } else println("Digital Signature is not configured")
    }else println("The sender account is either Frozen or Blocked")
  }

  /*
  Get all the transactions of the account
   */
  def getAllTransactions: List[Map[String, Any]] ={
    transactions
  }

  /*
  Get a certain transaction by ID
   */
  def getTransactionByID(id:Int): Map[String, Any] ={
    var transaction:Map[String, Any] = Map()
    breakable{
      transactions.foreach((elements: Map[String, Any]) => {
        if(elements.get("id").get.toString==id.toString){
          transaction = elements
          break
        } else null
      })
    }
    transaction
  }

  /*
  Get and display the account information
   */
  def getAccountInformation(): Map[String, Any] ={
    println("-"*60)
    println(s"Account ID: $id")
    println("Customer Name: "+customer.getFullName)
    println(s"Status: $status")
    println(s"Account Number: $accountNumber")
    println(s"Sort Code: $sortCode")
    println(s"Balance: $balance")
    println("-"*60)
    var information:Map[String,Any] = Map("id"-> id, "Customer"-> customer, "Status"->status,"AccountNumber"->accountNumber, "SortCode"-> sortCode, "Balance"->balance)
    information
  }

  /*
  Do transaction to an account number and sort code
   */
  def doTransactionByAccountNumber(accountNum:Int, sortcode:Int, amount: Double, concept: String, digitalSignature: Int): Unit = {
    if (status == "Active") {
      if (customer.isDigitalSignatureSet) {
        if (amount <= balance) {
          if (customer.checkDigitalSignature(digitalSignature)) {
            val destinatary = bank.getAccountByAccNumber(accountNum, sortcode)
            if (destinatary != null) {
              if (destinatary.status == "Active") {
                val now = Calendar.getInstance()
                decreaseBalance(amount)
                destinatary.increaseBalance(amount)
                transactions = transactions :+ Map("id" -> bank.transactions.length, "sender" -> this, "destinatary" -> destinatary, "amount" -> amount, "concept" -> concept, "date" -> now.getTime)
                destinatary.transactions = destinatary.transactions :+ Map("id" -> bank.transactions.length, "sender" -> this, "destinatary" -> destinatary, "amount" -> ("+"+amount.toString), "concept" -> concept, "date" -> now.getTime)
                bank.transactions = bank.transactions :+ Map("id" -> bank.transactions.length, "sender" -> this, "destinatary" -> destinatary, "amount" -> amount, "concept" -> concept, "date" -> now.getTime)
              } else println("The destinatary account is either Frozen or Blocked")
            } else println("The Account Number or Sort Code are incorrect.")
          } else println("Wrong Digital Signature")
        } else println("Insufficient balance")
      } else println("Digital Signature is not configured")
    } else println("The sender account is either Frozen or Blocked")
  }

  /*
  Do a transaction to a phone number bound to an account
   */
  def doTransactionByPhoneNumber(phoneNumber:String, amount: Double, concept: String, digitalSignature: Int): Unit = {
    if (status == "Active") {
      val destinatary = bank.getCustomerByPhoneNumber(phoneNumber)
      if(destinatary != null) {
        if(customer.accountForPhoneTransactions == this) {
          if(destinatary.accountForPhoneTransactions != null) {
            if (customer.isDigitalSignatureSet) {
              if (amount <= balance) {
                if (customer.checkDigitalSignature(digitalSignature)) {
                  if (destinatary.accountForPhoneTransactions.status == "Active") {
                    val now = Calendar.getInstance()
                    decreaseBalance(amount)
                    destinatary.accountForPhoneTransactions.increaseBalance(amount)
                    transactions = transactions :+ Map("id" -> bank.transactions.length, "sender" -> this, "destinatary" -> destinatary.accountForPhoneTransactions, "amount" -> ("-"+amount.toString), "concept" -> concept, "date" -> now.getTime)
                    destinatary.accountForPhoneTransactions.transactions = destinatary.accountForPhoneTransactions.transactions :+ Map("id" -> bank.transactions.length, "sender" -> this, "destinatary" -> destinatary.accountForPhoneTransactions, "amount" -> ("+"+amount.toString), "concept" -> concept, "date" -> now.getTime)
                    bank.transactions = bank.transactions :+ Map("id" -> bank.transactions.length, "sender" -> this, "destinatary" -> destinatary, "amount" -> amount, "concept" -> concept, "date" -> now.getTime)
                  } else println("The destinatary account is either Frozen or Blocked")
                } else println("Wrong Digital Signature")
              } else println("Insufficient balance")
            } else println("Digital Signature is not configured")
          } else println("The destinatary has the transactions through phone number disabled.")
        } else println("Transactions to phone numbers are disabled or bound to a different account.")
      } else println("The Phone Number doesn't belong to any customer.")
    } else println("The sender account is either Frozen or Blocked")
  }

  /*
  Display the information of a transaction.
   */
  def displayTransaction(transaction:Map[String,Any]): Unit ={
    println("Transaction ID: "+ transaction.get("id").get)
    println("Date: "+ transaction("date"))
    println("Sender Name: "+ (transaction("sender")).asInstanceOf[Account].customer.getFullName)
    println("Sender Account Number: "+ (transaction("sender")).asInstanceOf[Account].accountNumber)
    println("Sender Sort Code: "+ (transaction("sender")).asInstanceOf[Account].sortCode)
    println("Destinatary Name: " + (transaction("destinatary")).asInstanceOf[Account].customer.getFullName)
    println("Destinatary Account Number: " + (transaction("destinatary")).asInstanceOf[Account].accountNumber)
    println("Destinatary Sort Code: " + (transaction("destinatary")).asInstanceOf[Account].sortCode)
    println("Amount Transfered: "+ transaction("amount"))
    println("Concept: "+transaction("concept"))
  }

  /*
  Enable the transactions to a phone number
   */
  def enableTransactionsThroughPhoneNumber: Unit ={
    customer.accountForPhoneTransactions = this
  }

  /*
  Disable the transactions to a phone number
   */
  def disableTransactionsThroughPhoneNumber: Unit = {
    customer.accountForPhoneTransactions = null
  }

  /*
  Check if the transactions to a phone number are enabled.
   */
  def checkTransactionsThroughPhoneNumber: Boolean ={
    customer.accountForPhoneTransactions == this
  }

  /*
  Request a loan which needs to be approved by the bank
   */
  def requestLoan(amount:Double, interests:Double, loantype:String, lengthLoan:Int): Loan ={
    var loan = new Loan (this, amount, interests, loantype, lengthLoan)
    addLoanToDatabase(loan)
    loan.displayLoanStatus()
    loan
  }

  /*
  Adds the loan request to the database
   */
  def addLoanToDatabase(loan:Loan): Unit ={
    val url = "jdbc:mysql://localhost:3306/banking"
    val username = "root"
    val password = "root"
    // connection instance creation
    var connection: Connection = null
    try {
      Class.forName("com.mysql.jdbc.Driver")
      connection = DriverManager.getConnection(url, username, password)
      val insertMySQL = """INSERT INTO loans (id,accountID, customerID,amount,interests, loanType, length, status) values(?,?,?,?,?,?,?,?)"""
      val preparedStatement: PreparedStatement = connection.prepareStatement(insertMySQL)
      preparedStatement.setInt(1, loan.id)
      preparedStatement.setInt(2, id)
      preparedStatement.setInt(3, customer.id)
      preparedStatement.setDouble(4, loan.amountRequested)
      preparedStatement.setDouble(5, loan.interest)
      preparedStatement.setString(6, loan.loanType)
      preparedStatement.setInt(7, loan.lengthOfLoan)
      preparedStatement.setString(8, loan.status)
      preparedStatement.execute()
    } catch {
      case e: Exception => e.printStackTrace()
    }
    finally {
      connection.close()
    }
  }

  /*
  Add credit card to the database
   */
  def addCreditCardToDatabase(card: CreditCard): Unit = {
    val url = "jdbc:mysql://localhost:3306/banking"
    val username = "root"
    val password = "root"
    // connection instance creation
    var connection: Connection = null
    try {
      Class.forName("com.mysql.jdbc.Driver")
      connection = DriverManager.getConnection(url, username, password)
      val insertMySQL = """INSERT INTO creditcards (id,accountID, customerID, fullName,status, cardNumber, expiryDate, cvv, cardType, pinNumber, cashierLimit, purchaseLimit, balance) values(?,?,?,?,?,?,?,?,?,?,?,?,?)"""
      val preparedStatement: PreparedStatement = connection.prepareStatement(insertMySQL)
      preparedStatement.setInt(1, card.id)
      preparedStatement.setInt(2, id)
      preparedStatement.setInt(3, customer.id)
      preparedStatement.setString(4, customer.getFullName)
      preparedStatement.setString(5, card.status)
      preparedStatement.setString(6, card.cardNumber)
      preparedStatement.setString(7, card.expiryDate)
      preparedStatement.setString(8, card.CVV)
      preparedStatement.setString(9, card.cardType)
      preparedStatement.setInt(10, card.pinNumber)
      preparedStatement.setDouble(11, card.cashierWithdrawLimit)
      preparedStatement.setDouble(12, card.purchaseLimit)
      preparedStatement.setDouble(13, card.balance)
      preparedStatement.execute()
    } catch {
      case e: Exception => e.printStackTrace()
    }
    finally {
      connection.close()
    }
  }

  /*
  Freeze a certain credit card
   */
  def freezeCreditCard(card:CreditCard):Unit ={
    card.status = "Frozen"
  }

  /*
  Unfreeze a certain credit card
   */
  def unfreezeCreditCard(card: CreditCard): Unit = {
    card.status = "Active"
  }
}
