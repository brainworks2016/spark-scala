package com.dsm.assignments

import scala.util.control.Breaks._

object PrimeNumberTest {

  def main(args: Array[String]): Unit = {

    val numRange = 1 to 1000
    val primeNumCount = numRange.filter{num =>
      isPrime(num).getOrElse(false)
  }.length
  println(s"# of prime number = $primeNumCount ")
}

  def isPrime (num: Int): Option[Boolean] = {
  if (num <= 1)
  None
  else {

  var counter = 0
  breakable {
  for (j <- 2 to num / 2) {
  if (num % j == 0) {
  counter = 1
  break
}
}
}
  if (counter == 0)
  Some (true)
  else
  Some (false)
}
}

}