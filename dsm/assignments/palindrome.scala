package com.dsm.assignments

object palindrome {

  def main(args: Array[String]): Unit = {
    var number = 121
    val original_copy = number
    var reversedNumber = 0
    var temp = 0

    while (  number > 0)
    { //get the last digit
      temp = number % 10
      //create the reversed number
      reversedNumber = reversedNumber * 10 + temp
      // divide no to get component
      number = number/10
      // println(reversedNumber)
    }
    if (original_copy == reversedNumber)
      println("palindrome number")
    else
      println("not a palindrome number")
  }

}