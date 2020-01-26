package com.dsm.intro

/**
  * Created by sidhartha.ray on 04-11-2017.
  */
object PatternMatching {
  def main(args: Array[String]): Unit = {
    val age = "adult"
    age match {
      case "child" =>
        println("Age: Lesser than 18")
        //
      case "adult" =>
        println("Age: Between 18 to 65")
        //
      case _ =>
        println("Age: Greater than 65")
        //
    }
/*
    val age = 25
    (age < 18) match {
      case true =>
        println("child")
      case false =>
        (age >= 18 && age < 65) match {
          case true =>
            println("adult")
          case false =>
            println("old")
        }
    }
*/

  }
}
