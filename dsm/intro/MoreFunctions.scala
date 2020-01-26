package com.dsm.intro

/**
  * Created by sidhartha.ray on 04-11-2017.
  */
object MoreFunctions {
  def main(args: Array[String]): Unit = {

    // Calling a function variable length argument
    var sum = add(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    println("Variable length argument - sum = " + sum)

    // Calling function with default argument
    val defArgSum = defArg(10)
    println("Default argument - sum = " + defArgSum)

    // Function composition
    var result = multiplyBy5(add5(10))
    println("Function composition - multiplyBy5(add5(num)) = " + result)

    // Passing a function as a parameter - Higher order function (1)
    def multiplyBy2 (a: Int): Int = {
      a * 2
    }
    doSomething(25, multiplyBy2)

    // Passing a lambda as a parameter - Higher order function (2)
    var devideBy2 = (a: Int) => (a / 2)
    doSomething(25, devideBy2)

    var sumVal = reduce(List(1, 2, 3), add)
    print("Another higher order function - reduce() - value = " + sumVal)

  }

  // Function with Variable Length Argument
  def add(args: Int*):Int = {
    var sum = 0
    for(num <- args)
      sum = sum + num
    return sum
  }

  // Function with default argument
  def defArg(n1: Int, n2: Int = 10): Int = {
    return n1 + n2
  }

  // Function taking another function as a parameter - Higher order function
  def doSomething (num: Int, f: Int => Int): Unit = {
    println("Higher order function - doSomething() - value = " + f(num))
  }

  def add5(a: Int): Int = {
    a + 5
  }

  def multiplyBy5(a: Int): Int = {
    a * 5
  }

  // Function taking one collection and an another function as a parameter
  def reduce(data: List[Int], f: (List[Int]) => Int):Int = {
    return f(data)
  }

}
