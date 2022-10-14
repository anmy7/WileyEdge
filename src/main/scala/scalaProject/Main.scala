package scalaProject

object Main extends App{
  var santander = new Bank("Santander Bank", "SWEDREF")
  var nico = new Customer(santander, "Nicolas", "Andres", "Mesa Yip", "16/04/2000", "07776228621", "6 Oaten Hill")
  var p = santander.createCustomer("Nicolassss", "Andres", "Mesa Yip", "16/04/2000", "07776228621", "6 Oaten Hill")

  println(santander.getAllCustomersToString)
  nico.setDigitalSignature(222222)
  p.setDigitalSignature(333333)
  //  println(santander.getAllCustomersToString)
    println(santander.getInformationOfCustomer(santander.getCustomerByName("Nicolas Andres Mesa Yip")))
  //  santander.getBankInformation
  var account = new Account(0, nico, santander, "Savings", 500, "Active")
  var account2 = new Account(1, p, santander, "Savings", 1000, "Active")

  account.doTransaction(account2, 25, "Test", 222222)
  account2.doTransaction(account, 30, "Test2", 333333)
  account.doTransaction(account2, 50, "Test3", 222222)

  //  println(account.checkBalance)
  //  println(account2.checkBalance)
  println(santander.transactions)
  println(account.getTransactionByID(3))

}
