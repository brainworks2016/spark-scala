package com.dsm.assignments

object PalindromeOption {

  def main(args: Array[String]): Unit = {
  /* val number = 121
   val palindrome =  isPalindrome(number).getOrElse(false)
    println(s" $number is palindrome => $palindrome ") */
  val numRange = 1 to 200
    val palindromeCount = numRange.filter{num =>
      isPalindrome(num).getOrElse(false)
    }.length
    println(s"# of prime number = $palindromeCount ")

  }

  def isPalindrome (number1: Int): Option[Boolean] = {
    var number = number1
    val original_copy = number
    var reversedNumber = 0
    var temp = 0
    while (  number > 0)
    {
      temp = number % 10   //get the last digit
      reversedNumber = reversedNumber * 10 + temp   //create the reversed number
      number = number/10    // divide no to get component
    }
    if (original_copy == reversedNumber)
      Some (true)
    else
      Some (false)
  }
}
