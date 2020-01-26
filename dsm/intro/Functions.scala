package com.dsm.intro

/**
  * Created by sidhartha.ray on 04-11-2017.
  */
object Functions {
  def main(args: Array[String]): Unit = {

    // Function call
    println(sayHello("Pratik"))

    // Calling a function with multiple parameter list
    println(sayHelloAgain("Pratik")("Sidharth"))

    // Calling a higher order function
    def provideYourName(): String = {
      "Sidharth"
    }
    println(sayHelloOnceAgain("Pratik")(provideYourName))

    // Calling a higher order function by passing a lambda
    println(sayHelloOnceAgain("Pratik")(() => "Sidharth"))

    // Calling a function with implicit parameter list
    implicit val myName = "Sidharth"
    println(sayHelloImplicitly("Pratik"))

    // Calling a higher order function with implicit parameter list
    implicit def provideYourNameAgain(): String = {
      "Sidharth"
    }
    println(sayHelloAgainImplicitly("Pratik"))

  }

  def sayHello(name: String): String = {
    "Hello " + name + "!"                     // String concatenation
  }

  // Multiple parameter list
  def sayHelloAgain(name: String)(myself: String): String = {
    s"Hello $name! My name is $myself"        // Called string interpolation
  }

  // Higher order functiom
  def sayHelloOnceAgain(name: String)(whoAreYou: () => String): String = {
    s"Hello $name! My name is ${whoAreYou()}"
  }

  // Implicit parameter list
  def sayHelloImplicitly(name: String)(implicit myself: String): String = {
    s"Hello $name! My name is $myself"
  }

  // Higher order function with implicit parameter list
  def sayHelloAgainImplicitly(name: String)(implicit whoAreYou: () => String): String = {
    s"Hello $name! My name is ${whoAreYou()}"
  }

}
