package scalaProject

import java.sql._
import scala.collection.mutable.ListBuffer
import scala.io.Source

object Main extends App{
  val url = "jdbc:mysql://localhost:3306/banking"
  val username = "root"
  val password = "root"
  def readFromFile: scala.Array[String] ={
    var data:scala.Array[String] = scala.Array()
    Source.fromFile("C:\\Users\\Nico\\IdeaProjects\\WileyEdge\\src\\main\\scala\\scalaProject\\Variables.txt").getLines().foreach((element)=>{
      var list:scala.Array[String] = element.split(",")
      data = list
    })
    data
  }

  def loadCustomersFromDatabase(bank:Bank): Unit ={
    // connection instance creation
    var connection: Connection = null
    try {
      Class.forName("com.mysql.jdbc.Driver")
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery("select firstName,middleName, lastName, birthDate, phoneNumber, address from banking.customers; ")
      while (resultSet.next()) {
        val firstName = resultSet.getString("firstName")
        val middleName = resultSet.getString("middleName")
        val lastName = resultSet.getString("lastName")
        val birthDate = resultSet.getString("birthDate")
        val phoneNumber = resultSet.getString("phoneNumber")
        val address = resultSet.getString("address")
        new Customer(bank, firstName, middleName, lastName, birthDate, phoneNumber, address)
      }
    } catch {
      case e: Exception => e.printStackTrace()
    }
    finally {
      connection.close()
    }
  }

  def loadAccountsFromDatabase(): Unit = {
    // connection instance creation
    var connection: Connection = null
    try {
      Class.forName("com.mysql.jdbc.Driver")
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery("select customerID,accountNumber,sortCode, accountType, balance, status from banking.accounts; ")
      while (resultSet.next()) {
        val customerID = resultSet.getInt("customerID")
        val accountNumber = resultSet.getInt("accountNumber")
        val sortCode = resultSet.getInt("sortCode")
        val accountType = resultSet.getString("accountType")
        val balance = resultSet.getDouble("balance")
        val status = resultSet.getString("status")
        val customer = new Account(bofa.getCustomerByID(customerID), bofa, accountType, balance, status)
        customer.accountNumber = accountNumber
        customer.sortCode = sortCode
      }
    } catch {
      case e: Exception => e.printStackTrace()
    }
    finally {
      connection.close()
    }
  }

  def loadLoansFromDatabase(): Unit = {
    // connection instance creation
    var connection: Connection = null
    try {
      Class.forName("com.mysql.jdbc.Driver")
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery("select accountID,amount,interests, loanType, length, status from banking.loans; ")
      while (resultSet.next()) {
        val accountID = resultSet.getInt("accountID")
        val amount = resultSet.getDouble("amount")
        val interests = resultSet.getDouble("interests")
        val loanType = resultSet.getString("loanType")
        val length = resultSet.getInt("length")
        val status = resultSet.getString("status")
        val loan = new Loan(bofa.accounts(accountID), amount, interests, loanType, length)
        loan.status = status
      }
    } catch {
      case e: Exception => e.printStackTrace()
    }
    finally {
      connection.close()
    }
  }

  def loadCreditCardsFromDatabase(): Unit = {
    // connection instance creation
    var connection: Connection = null
    try {
      Class.forName("com.mysql.jdbc.Driver")
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery("select accountID,status, cardNumber, expiryDate, cvv, cardType, pinNumber, cashierLimit, purchaseLimit, balance from banking.creditcards; ")
      while (resultSet.next()) {
        val accountID = resultSet.getInt("accountID")
        val status = resultSet.getString("status")
        val cardNumber = resultSet.getString("cardNumber")
        val expiryDate = resultSet.getString("expiryDate")
        val cvv = resultSet.getString("cvv")
        val cardType = resultSet.getString("cardType")
        val pinNumber = resultSet.getInt("pinNumber")
        val cashierLimit = resultSet.getDouble("cashierLimit")
        val purchaseLimit = resultSet.getDouble("purchaseLimit")
        val balance = resultSet.getDouble("balance")
        val card = new CreditCard(bofa.accounts(accountID), cardType, cashierLimit, purchaseLimit, balance)
        card.status = status
        card.cardNumber = cardNumber
        card.expiryDate = expiryDate
        card.CVV = cvv
        card.pinNumber = pinNumber
      }
    } catch {
      case e: Exception => e.printStackTrace()
    }
    finally {
      connection.close()
    }
  }


  var bankData:scala.Array[String] = readFromFile
  var bofa = new Bank(bankData(0), bankData(1))
  loadCustomersFromDatabase(bofa)
  loadAccountsFromDatabase()
  loadLoansFromDatabase()
  loadCreditCardsFromDatabase()
  var nico = bofa.customers(0)
  var p = bofa.customers(1)
//  var card= p.accounts(0).createCreditCard("Basic", 500,500,1000)
  println(bofa.creditCards)

}
