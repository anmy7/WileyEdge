package scalaProject

object Main extends App{
  var santander = new Bank("Santander Bank", "SWEDREF")
  var nico = santander.createCustomer( "Nicolas", "Andres", "Mesa Yip", "16/04/2000", "07776228621", "6 Oaten Hill")
  var p = santander.createCustomer("Nicolassss", "Andres", "Mesa Yip", "16/04/2000", "07776228622", "6 Oaten Hill")
  var account = nico.createAccount("Savings", 500)
  var account3 = nico.createAccount("Savings2", 1500)
  var account2 = p.createAccount("Savings", 1000)

  account.enableTransactionsThroughPhoneNumber
  account2.enableTransactionsThroughPhoneNumber
  nico.setDigitalSignature(111111)
  account.doTransactionByPhoneNumber("07776228622", 30, "test", 111111)
  println(account2.transactions)
//  account.displayTransaction(account.getTransactionByID(0))
  println(account2.checkBalance)
}
