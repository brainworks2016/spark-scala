package com.dsm.assignments

object HigherFunc {
  def main(args: Array[String]): Unit = {
    // Calling a higher order function
    def provideYourName(): String = {
      "Sidharth"
    }
    println(sayHelloOnceAgain("Pratik")(provideYourName))
    // Higher order functiom
    def sayHelloOnceAgain(name: String)(whoAreYou: () => String): String = {
      s"Hello $name! My name is ${whoAreYou()}"
    }

    /*def add(x:Int,y:Int):Int = x+y
    println(sum (x1) (add(x,y)))
    def sum(x1:Int)(add)*/

    def addition(f: (Int, Int) => Int,a: Int, b:Int): Int = f(a,b)
    val intSum = (x: Int, y: Int) => (x + y)

    val normalSum = addition(intSum, 1, 2)
    println(normalSum)

 /*   def totalcost(donutType: String)(quantity: Int)(f: Double => Double): Double = {
      println(s"Calculating total cost for $quantity $donutType")
      val totalCost = 2.50 * quantity
      f(totalCost)
    }
      println(totalcost("mad") (10)(f = )*/

    //def sum(a:Int,b:Int):Int = a+b
   // def add(a: Int, b:Int , f:(Int,Int)=> Int) : Int =   f(a+b)
    //val res = add(50,20,(x,y)=>x+y)
    //println(res)
  }
}
