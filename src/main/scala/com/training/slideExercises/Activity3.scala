package com.training.slideExercises

object Activity3 extends App {
  def calculation(quantity: Int, price: Double): BigDecimal = {
    return BigDecimal(price * quantity).setScale(2)
  }

  print("$" + calculation(10, 2.50))

}
