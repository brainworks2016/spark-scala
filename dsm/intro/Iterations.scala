package com.dsm.intro

/**
  * Created by sidhartha.ray on 04-11-2017.
  */
object Iterations {
  def main(args: Array[String]): Unit = {

    // while loop - When termination condition isn't known
    println("Printing sum till 20 using while() loop :")
    var a = 10        // Initialization
    while(a <= 20){   // Condition
      println(a)
      a = a + 2       // Incrementation
    }
    println()
    // Another example on while loop
    // while(rs.hasNext){
    //   var emp = rs.next()
    // }

    // do-while loop - same as while loop, but it'll execute at least once
    println("Printing sum till 20 using do-while() loop :")
    var b = 10;       // Initialization
    do {
      println(b)
      b = b + 20;      // Increment
    } while(b <= 20)    // Condition
    println()

    // for loop - when both initial and termination condition is known to us, e.g. iterating collections
    for(c <- 1 to 10){  // initialization and termination condition
      println(c)
    }
  }
}
