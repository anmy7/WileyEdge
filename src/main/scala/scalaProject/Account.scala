package scalaProject

import java.util.Calendar
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
  generateAccountNumber()
//  setAccountNumber(11111111, 222222)


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

  def setAccountNumber(acc: Int, sort: Int): Unit = {
    accountNumber = acc
    sortCode = sort
  }

  def checkBalance: Double ={
    balance
  }

  def increaseBalance(amount:Double): Unit ={
    balance += amount
  }

  def decreaseBalance(amount: Double): Unit = {
    balance -= amount
  }

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

  def getAllTransactions: List[Map[String, Any]] ={
    transactions
  }


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

  def enableTransactionsThroughPhoneNumber: Unit ={
    customer.accountForPhoneTransactions = this
  }

  def disableTransactionsThroughPhoneNumber: Unit = {
    customer.accountForPhoneTransactions = null
  }

  def checkTransactionsThroughPhoneNumber: Boolean ={
    customer.accountForPhoneTransactions == this
  }
}
