package com.dsm.oops

/**
  * Created by sidhartha.ray on 05-11-2017.
  */
object SingletonDemo {
  def main(args: Array[String]): Unit = {
    val empPratik = new Emp("Pratik", "Solanki", 100)   // general way of instantiating a class
    println(empPratik)
    println("Pratik belongs " + empPratik.getDepartmentName("R&D") + " department.")
    empPratik.awardMoreStocks(20)
    println("After adding 20 more stocks,")
    println(empPratik)

    val empSidharth = Emp("Sidhartha", "Ray", 1000)                                   // instantiating a class using 'apply' function
    println(empSidharth)
  }
}

// Singleton object & Companion object of Emp class
object Emp {
  // object secret
  private val departmentCodeByName: Map[String, String] = Map(
    "HR" -> "Human Resouces!",
    "ACT" -> "Accounts",
    "R&D" -> "Resesrch & Development",
    "MKT" -> "Marketing",
    "SLS" -> "Sales",
    "OPS" -> "Operation"
  )

  // 'apply' function to instantiate companion class' object without using 'new' keyword
  def apply(firstName: String, lastName: String, stocks: Double) = new Emp(firstName, lastName, stocks)
}

// Companion class of Emp object
class Emp (firstName: String, lastName: String, var stocks: Double) {
  // class secret

  def getFirstName(): String = {
    return firstName
  }

  def getLasttName(): String = {
    return lastName
  }
  def getStocks(): Double = {
    return stocks
  }

  def awardMoreStocks(numberOfStocks: Int): Unit = {
    stocks = stocks + numberOfStocks
  }

  def getDepartmentName(code: String): String = {
    Emp.departmentCodeByName.getOrElse(code, s"The department with $code code doesn't exists!")
  }

  override def toString: String = s"Emp($firstName,$lastName,$stocks)"
}
