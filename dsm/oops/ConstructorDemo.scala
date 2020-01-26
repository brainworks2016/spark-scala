package com.dsm.oops

/**
  * Created by sidhartha.ray on 05-11-2017.
  */
object ConstructorDemo {
  def main(args: Array[String]): Unit = {
    val empSidharth = new Employee(101, "Sidhartha")    // Passing values to constructor
    println(empSidharth)                                            // prints some hash representation of the object, not readable
    empSidharth.showEmployeeDetails()                               // Calling a function by using an object
//    empSidharth.id                                                // Can't access the private member of a class

    val empSachin = new Employee(102, "Sachin")
    empSachin.showEmployeeDetails()

    var bigDataEmployee101 = new BigDataEmployee (101, 3000000)
    println("BigDataEmployee101's id = " + bigDataEmployee101.id)   // Can access val member of the class
//    smartEmployee101.id = 10                                      // but can't modify the val member of the class
    bigDataEmployee101.salary = 3500000
    println(bigDataEmployee101)             // no more hash representation of the object, internally toString() method has been called

    var bigDataEmployee102 = new BigDataEmployee(102, "Sachin", 3500000)
    println(bigDataEmployee102)
  }
}


class Employee (id: Int, name: String) {              // Class takes parameters as well and by default private vals
  def showEmployeeDetails(): Unit = {                 // Class body is the default constructor
    println(s"Employee($id,$name)")
  }
}

class BigDataEmployee(val id: Int, var salary: Int) {  // Class parameters can also be prefixed with var or val
  var name:String = null

  def this(id: Int, name: String, salary: Int) {
    this(id, salary)
    this.name = name
  }

  override def toString: String = {
    s"BigDataEmployee($id,$name,$salary)"
  }
}

