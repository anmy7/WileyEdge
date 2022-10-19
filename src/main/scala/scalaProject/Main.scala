package scalaProject

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Main extends App{

  def readFromFile: ListBuffer[Array[String]] ={
    var data:ListBuffer[Array[String]] = ListBuffer()
    Source.fromFile("C:\\Users\\Nico\\IdeaProjects\\WileyEdge\\src\\main\\scala\\classActivities\\Variables.txt").getLines().foreach((element)=>{
      var list:Array[String] = element.split(",")
      data += list
    })
    data
  }

  var bankData:ListBuffer[Array[String]] = readFromFile
  var bofa = new Bank(bankData(0)(0), bankData(0)(1))
  var santander = new Bank(bankData(1)(0), bankData(1)(1))
  var nico = bofa.createCustomer( "Nicolas", "Andres", "Mesa Yip", "16/04/2000", "07776228621", "6 Oaten Hill")
  var p = bofa.createCustomer("Nicolassss", "Andres", "Mesa Yip", "16/04/2000", "07776228622", "6 Oaten Hill")
  var account = nico.createAccount("Savings", 500000)
  var account3 = nico.createAccount("Savings2", 1500)
  var account2 = p.createAccount("Savings", 1000)
//  var loan = account.requestLoan(500000, 3.5, "test", 5)
  var card= account.createCreditCard("Prepaid", 500,500,1000)
  card.displayCreditCardInformation
  println(card.setPinNumber(1111))
  card.increaseBalanceOfCardFromCashier(20000, 1111)
  nico.setDigitalSignature(111111)
  card.changePurchaseOrWithdrawLimits(111111, 50000, 200000)
  card.displayCreditCardInformation
  card.payUsingCreditCard(account3, 1000, 1111)
  account.freezeCreditCard(card)
  card.increaseBalanceOfCardFromAccount(2000,111111)
  card.displayCreditCardInformation
  println(bofa.transactions)
}
