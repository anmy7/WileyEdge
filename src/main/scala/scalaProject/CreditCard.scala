package scalaProject

import java.text.SimpleDateFormat
import java.util.Calendar
import scala.util.Random
import scala.util.control.Breaks._

class CreditCard (Account: Account, cardtype:String, cashierLimit:Double, purchaselimit:Double, Balance:Double = 0){
  var status: String = "Active"
  var account:Account = Account
  var customer:Customer = account.customer
  var bank:Bank = account.bank
  var id:Int = bank.creditCards.length
  account.creditCards += this
  bank.creditCards += this
  var cardNumber:String = ""
  var expiryDate:String = ""
  var CVV: String = ""
  var pinNumber: Int = 0
  var cardType:String = cardtype
  var cashierWithdrawLimit: Double = cashierLimit
  var purchaseLimit:Double = purchaselimit
  var chipID: String = ""
  var balance:Double = Balance
  val format = new SimpleDateFormat("MM/yy")
  var transactions:List[Map[String, Any]] = List()
  val now: Calendar = Calendar.getInstance()
  generateCreditCard
  generateChipID
  setPinNumber()

  /*
  Generate the credit card number, cvv and expiry date
   */
  def generateCreditCard: Unit ={
    var unique:Boolean = true
    var cNumber: Long = 0
    var cvv: Int = 0
    val now = Calendar.getInstance()
    now.add(Calendar.YEAR, 5)
    var expiry = format.format(now.getTime)
    while(unique){
      cNumber = Random.nextLong(8999999999999999L) + 1000000000000000L
      cvv = Random.nextInt(899) + 100

      breakable {
        bank.creditCards.foreach((creditcard: CreditCard) => {
          if(creditcard.cardNumber == cNumber.toString){
            unique = true
            break()
          } else unique = false
        })
        cardNumber = cNumber.toString
        CVV = cvv.toString
        expiryDate = expiry
      }
    }
  }

  /*
    Generate the Chip id
   */
  def generateChipID: Unit ={
    var unique: Boolean = true
    var chipID: Long = 0
    while (unique) {
      chipID = Random.nextLong(899999999999999999L) + 100000000000000000L

      breakable {
        bank.creditCards.foreach((creditcard: CreditCard) => {
          if (creditcard.chipID == chipID.toString) {
            unique = true
            break()
          } else unique = false
        })
        this.chipID = chipID.toString
      }
    }
  }

  /*
  Set the Pin number
   */
  def setPinNumber(number:Int = 0): Unit ={
    if(number.toString.length==4){
      pinNumber = number
    } else pinNumber = Random.nextInt(8999) + 1000
  }

  /*
  Change the pin number
   */
  def changePinNumber(actualPin:Int, newPin:Int, digitalSignature:Int): Unit ={
    if(customer.isDigitalSignatureSet) {
      if(customer.checkDigitalSignature(digitalSignature)) {
        if (pinNumber == actualPin) {
          pinNumber = newPin
        }
      }
    }
  }

  /*
  Change the purchase and withdraw limits
   */
  def changePurchaseOrWithdrawLimits(digitalSignature:Int, purchaselimit:Double = purchaseLimit, withdrawlimit:Double = cashierWithdrawLimit): Unit ={
    if(customer.isDigitalSignatureSet) {
      if(customer.checkDigitalSignature(digitalSignature)) {
        if(purchaselimit != purchaseLimit){
          purchaseLimit = purchaselimit
        }
        if (withdrawlimit != cashierWithdrawLimit) {
          cashierWithdrawLimit = withdrawlimit
        }
      } else println("Wrong Digital Signature")
    }else println("Please configure your digital signature.")
  }

  /*
  Increase the balance of the prepaid credit card from the account
   */
  def increaseBalanceOfCardFromAccount(amount:Double, digitalSignature:Int): Unit ={
    if(status == "Active"){
      if(account.status == "Active") {
        if (customer.isDigitalSignatureSet) {
          if (customer.checkDigitalSignature(digitalSignature)) {
            if(amount < account.balance){
              balance += amount
              account.balance -= amount
              transactions = transactions :+ Map("id" -> bank.transactions.length, "sender" -> account, "destinatary" -> this, "amount" -> amount, "concept" -> "Recharge of Prepaid Card", "date" -> now.getTime)
              account.transactions = account.transactions :+ Map("id" -> bank.transactions.length, "sender" -> account, "destinatary" -> this, "amount" -> ("+" + amount.toString), "concept" -> "Recharge of Prepaid Card", "date" -> now.getTime)
              bank.transactions = bank.transactions :+ Map("id" -> bank.transactions.length, "sender" -> account, "destinatary" -> this, "amount" -> amount, "concept" -> "Recharge of Prepaid Card", "date" -> now.getTime)
            } else println("Insufficient balance in the account. ")
          } else println("Wrong Digital Signature")
        } else println("Please configure your digital signature.")
      } else println("The account is either blocked or frozen.")
    } else println("The credit card is either blocked or frozen.")
  }

  /*
  Increase the balance of the prepaid credit card from the cashier
   */
  def increaseBalanceOfCardFromCashier(amount: Double, pin: Int): Unit = {
    if (status == "Active") {
      if (account.status == "Active") {
        if (pinNumber == pin) {
          if(cardType == "Prepaid") {
            balance += amount
            transactions = transactions :+ Map("id" -> bank.transactions.length, "sender" -> "Cashier", "destinatary" -> this, "amount" -> amount, "concept" -> "Recharge of Prepaid Card through Cashier", "date" -> now.getTime)
            bank.transactions = bank.transactions :+ Map("id" -> bank.transactions.length, "sender" -> "Cashier", "destinatary" -> this, "amount" -> amount, "concept" -> "Recharge of Prepaid Card through Cashier", "date" -> now.getTime)
          } else {
            account.increaseBalance(amount)
            transactions = transactions :+ Map("id" -> bank.transactions.length, "sender" -> "Cashier", "destinatary" -> account, "amount" -> amount, "concept" -> "Cashier Deposit", "date" -> now.getTime)
            bank.transactions = bank.transactions :+ Map("id" -> bank.transactions.length, "sender" -> "Cashier", "destinatary" -> account, "amount" -> amount, "concept" -> "Cashier Deposit", "date" -> now.getTime)
          }
        } else println("Wrong Pin Number")
      } else println("The account is either blocked or frozen.")
    } else println("The credit card is either blocked or frozen.")
  }

  /*
  Pay using the credit card
   */
  def payUsingCreditCard(destinatary:Account, amount:Double, pin:Int): Unit ={
    if (status == "Active") {
      if (account.status == "Active") {
        if(destinatary.status == "Active") {
          if (pinNumber == pin) {
            if (cardType == "Prepaid") {
              if(amount <= balance) {
                balance -= amount
                transactions = transactions :+ Map("id" -> bank.transactions.length, "sender" -> this, "destinatary" -> destinatary, "amount" -> ("-"+amount.toString), "concept" -> s"Payment at Business: ${destinatary.customer.getFullName}", "date" -> now.getTime)
                destinatary.transactions = destinatary.transactions :+ Map("id" -> bank.transactions.length, "sender" -> this, "destinatary" -> destinatary, "amount" -> ("+"+amount.toString), "concept" -> s"Payment at Business: ${destinatary.customer.getFullName}", "date" -> now.getTime)
                bank.transactions = bank.transactions :+ Map("id" -> bank.transactions.length, "sender" -> this, "destinatary" -> destinatary, "amount" -> (amount.toString), "concept" -> s"Payment at Business: ${destinatary.customer.getFullName}", "date" -> now.getTime)
              } else println("Insufficient Funds.")
            } else {
              if(amount <= account.checkBalance) {
                account.decreaseBalance(amount)
                transactions = transactions :+ Map("id" -> bank.transactions.length, "sender" -> this, "destinatary" -> destinatary, "amount" -> ("-" + amount.toString), "concept" -> s"Payment at Business: ${destinatary.customer.getFullName}", "date" -> now.getTime)
                destinatary.transactions = destinatary.transactions :+ Map("id" -> bank.transactions.length, "sender" -> this, "destinatary" -> destinatary, "amount" -> ("+" + amount.toString), "concept" -> s"Payment at Business: ${destinatary.customer.getFullName}", "date" -> now.getTime)
                bank.transactions = bank.transactions :+ Map("id" -> bank.transactions.length, "sender" -> this, "destinatary" -> destinatary, "amount" -> (amount.toString), "concept" -> s"Payment at Business: ${destinatary.customer.getFullName}", "date" -> now.getTime)              }
            }
          } else println("Wrong Pin Number")
        }
      } else println("The account is either blocked or frozen.")
    } else println("The credit card is either blocked or frozen.")
  }

  /*
  Display the credit card information
   */
  def displayCreditCardInformation: Unit ={
    println(s"CreditCard Owner: ${customer.getFullName}")
    println(s"Card Type: $cardType")
    println(s"Card Status: $status")
    if(cardType == "Prepaid") {
      println(s"Balance: $balance")
    }
    println(s"Cashier Withdraw Limit: $cashierWithdrawLimit")
    println(s"Purchase Limit: $purchaseLimit")
    println("-"*40)
  }
}
