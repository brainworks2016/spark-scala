package com.dsm.intro

object VariableDeclaration {
  def main(args: Array[String]): Unit = {

    //val and var
    val text: String = "Not another word count example"
//    text = "try this" //(compilation error - "Reassignment to val")

    var changeMe: String = "This can be changed"
    changeMe = "Spark! here I come!"

    var changeMeAgain = "This can also be changed"

    // Some other Data Types
    var v3: Int = 10
    var v4: Boolean = false
    var v5: Double = 10.03

    var v6: BigInt = BigInt("1234567890987654321234567")
    println("BigInt: " + v6)

    var v7: BigDecimal = BigDecimal("4567890876543456.89")
    println("BigDecimal: " + v7)

    // Mathematical 
    import scala.math._
    println("10 to the power 2 = " + pow(10, 2))
    println("e to the power 1 = " + exp(1))
  }
}
