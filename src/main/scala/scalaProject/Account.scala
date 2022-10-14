package scalaProject

import java.util.Calendar
import scala.util.control.Breaks._

class Account(ID:Int, Customer:Customer, Bank:Bank, accounttype: String, Balance:Double, Status: String) {
  var id:Int = ID
  var accountType:String = accounttype
  var balance:Double = Balance
//  status["Active, Frozen, Blocked"]
  var status:String = Status
  var customer:Customer = Customer
  var bank:Bank = Bank
  var transactions:List[Map[String, Any]] = List()

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

}
