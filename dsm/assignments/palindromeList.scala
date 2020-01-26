package com.dsm.assignments

object palindromeList {

  def isPalindrome2[T](list: List[T]): Boolean = {
    val len = list.length
    for (i <- 0 until len / 2) {
      if (list(i) != list(len - i - 1))
        println("palindrome")
        return false
    }
    println("not palindrome")
    return true
  }

  def main(args: Array[String]): Unit = {
   // val numList = List(1, 3, 0, 3, 1)

  //  isPalindrome2[numList]("numList")

  }

}