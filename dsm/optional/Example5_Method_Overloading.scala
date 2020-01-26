package com.dsm.oops

object Example5_Method_Overloading {
  def main(args: Array[String]): Unit = {
    openAccount(101, "Saving").show()
    openAccount(101, "Current", 50000).show()
  }

  // function with 2 parameters
  def openAccount(acNum:Int, acType:String): Account = {
    var ac = new Account()
    ac.acNum = acNum
    ac.acType = acType
    return ac
  }

  // function with 3 parameters
  def openAccount(acNum:Int, acType:String, bal:Double): Account = {
    val ac = new Account()
    ac.acNum = acNum
    ac.acType = acType
    ac.bal = bal
    return ac
  }
}

class Account{
  var acNum:Int = 0
  var bal:Double = 0
  var acType:String = null

  def show(): Unit = {
    println("Account[" + acNum + ", " + acType + ", " + bal + "]")
  }
}

