package com.training.scalaProject
import org.scalatest.funsuite.AnyFunSuite

class Test extends AnyFunSuite{
  val loaddata = new LoadData
  val bofa = loaddata.bofa
  test("Test create and delete customers"){
    var oldsize = bofa.customers.length
    val customer1 = bofa.createCustomer("Chris", "Harry", "Scott", "12/05/2001", "07776443322", "235 road kent")
    assert(oldsize+1 == bofa.customers.length)
    assert(customer1.getFullName === "Chris Harry Scott")
    bofa.deleteCustomer(bofa.getCustomerByName("Chris Harry Scott"))
    assert(oldsize == bofa.customers.length)
  }

  test("Create bank accounts, credit cards and loans"){
    val customer1 = bofa.createCustomer("Chris", "Harry", "Scott", "12/05/2001", "07776443322", "235 road kent")
    val account1 = customer1.createAccount("Basic Account", 20000)
    val account2 = customer1.createAccount("Savings", 150000)
    val creditCard1 = account1.createCreditCard("Prepaid", 500, 500, 1000)
    val creditCard2 = account2.createCreditCard("Normal", 1000, 1000)
    assert(account1.creditCards.length == 1)
    assert(!customer1.isDigitalSignatureSet)
    customer1.setDigitalSignature(111111)
    assert(customer1.checkDigitalSignature(111111))
    bofa.deleteCustomer(customer1)

  }





}
