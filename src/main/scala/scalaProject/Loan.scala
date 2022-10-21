package scalaProject

import java.text.SimpleDateFormat
import java.util.Calendar

class Loan (Account:Account, amount:Double, interests:Double, loantype:String, lengthLoan:Int){
  val account:Account = Account
  val customer:Customer = account.customer
  val bank:Bank = customer.bank
  var id:Int = bank.loans.length
  customer.loans += this
  bank.loans += this
  var status:String = "Requested"
  var amountRequested:Double = amount
  var interest:Double = interests
  var amountToPay:Double = 0
  var amountAlreadyPayed:Double = 0
  var loanType:String = loantype
  var lengthOfLoan:Int = lengthLoan
  val now: Calendar = Calendar.getInstance()
  val format = new SimpleDateFormat("d-M-y")
  var dateRequested:String = format.format(now.getTime)
  var dateAccepted:String=""
  var dateEndLoan:String =""
  var dateLastPayment:String = ""

  /*
  Display the Loan status
   */
  def displayLoanStatus(): Unit = {
    println("-" * 40)
    println(s"Loan ID: $id")
    println("Customer name: " + customer.getFullName)
    println(s"Date Requested: $dateRequested")
    println(s"Loan Type: $loanType")
    println(s"Amount Requested: $amountRequested")
    println(s"Interests: $interest")
    println(s"Length of Loan: $lengthOfLoan")
    println(s"Status: $status")
    if (status == "Approved") {
      println("-" * 40)
      println(s"Amount Payed: $amountAlreadyPayed")
      println(s"Amount Debt: $getAmountDebt")
      println(s"Date Approval of Loan: $dateAccepted")
      println(s"Date End of Loan: $dateEndLoan")
    }
    if(status == "Payed"){
      println("-" * 40)
      println(s"Amount Payed: $amountAlreadyPayed")
      println("Date of Last Payment")
    }
  }

  /*
  Get the amount debt
   */
  def getAmountDebt: Double ={
    amountToPay-amountAlreadyPayed
  }

  /*
  Check if the loan was approved
   */
  def isLoanApproved: Boolean ={
    status == "Approved"
  }

  /*
  Check if the loan was payed.
   */
  def checkIfLoanIsPayed: Boolean ={
    amountToPay == amountAlreadyPayed
  }

  /*
  Pay certain amount of the loan
   */
  def payLoan(amount:Double, digitalSignature:Int): Unit ={
    if(account.status == "Active") {
      if(status == "Approved") {
        if (amount <= account.checkBalance) {
          if (customer.isDigitalSignatureSet) {
            if (customer.checkDigitalSignature(digitalSignature)) {
              if (amount <= getAmountDebt) {
                amountAlreadyPayed += amount
                account.decreaseBalance(amount)
              } else {
                amountAlreadyPayed += getAmountDebt
                account.decreaseBalance(getAmountDebt)
              }
              account.transactions = account.transactions :+ Map("id" -> bank.transactions.length, "sender" -> account, "destinatary" -> this, "amount" -> ("-" + amount.toString), "concept" -> ("Repaying Loan with ID "+id), "date" -> now.getTime)
              bank.transactions = bank.transactions :+ Map("id" -> bank.transactions.length, "sender" -> account, "destinatary" -> this, "amount" -> ("-" + amount.toString), "concept" -> ("Repaying Loan with ID "+id), "date" -> now.getTime)

              if(checkIfLoanIsPayed){
                status = "Payed"
                val now: Calendar = Calendar.getInstance()
                dateLastPayment = format.format(now.getTime)
              }
            }
          }
        }
      }
    }
  }

  /*
  Calculate the length of the loan
   */
  def calculateLengthOfLoan: String ={
    val now:Calendar = Calendar.getInstance()
    now.add(Calendar.YEAR, lengthOfLoan)
    format.format(now.getTime)
  }

  /*
  Updating the status when the bank accepts the loan
   */
  def acceptLoan: Unit ={
    if(status == "Requested"){
      status = "Approved"
      dateAccepted = format.format(Calendar.getInstance().getTime)
      dateEndLoan = calculateLengthOfLoan
      amountToPay = amountRequested + (amountRequested * interest/100)
    }
  }

  /*
  Display the debt information.
   */
  def displayDebtInformation: Unit ={
    println("-"*40)
    println(s"Loan ID: $id")
    println(s"Customer: ${customer.getFullName}")
    println(s"Amount Pending to be paid: $getAmountDebt")
    println(s"Pay before the $dateEndLoan")
  }
}
