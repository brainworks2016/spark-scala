package com.dsm.containers

/**
  * Created by sidhartha.ray on 14-11-2017.
  */
object ListExamples {
  def main(args: Array[String]): Unit = {

    // List is implemented as a Singly Linked List terninated with Nil
    val weekdaysCons = "Mon" :: "Tue" :: "Wed" :: "Thus" :: "Fri" :: Nil    // Creating List using cons operator or double colon operator
    println(weekdaysCons)

    val weekdays = List("Mon", "Tue", "Wed", "Thus", "Fri")                 // Creating List using List apply function
    val weekends = List("Sat", "Sun")
    val days = weekdays ::: weekends                                        // Concatenating multiple Lists, can use ++ operator as well
    println("After merging: " + days)

    val daysAgain = List(weekdays, weekends).flatten                        // Flattening List of List to List
    println("After flattening: " + daysAgain)

    val dayIndices = List(1, 2, 3, 4, 5, 6, 7)
    println("zip days and day indices: " + (days.zip(dayIndices)))           // zip function object
    // Some common functions on List
    println("weekdays head = " + weekdays.head)
    println("weekdays tail = " + weekdays.tail)
    println("weekdays's 2nd index = " + weekdays(1))
    println("weekdays contains 'Mon'? " + weekdays.contains("Mon"))
    println("weekdays size = " + weekdays.size)
    println("Reverse of weekdays = " + weekdays.reverse)

    // Iterating a List
    println("\nIterating a list: ")
    for(day <- weekdays)
      println(day)

    /*********************************** Higher order methods ********************************************/

    // 1. Map, Foreach, Filter
    println("\nApplying foreach() operation,")
    weekdays.foreach{println(_)}                                            // weekdays.foreach(day => println(day))
    weekdays.foreach(rec => println(rec))

    println("\nApplying map() operation,")
    weekdays.map(_ == "Mon").foreach(day => print(day + " "))               // weekdays.map(day => day == "Mon")...

    println("\n\nApplying filter() operation,")
    weekdays.filter(_ == "Mon").foreach(day => print(day + " "))            // weekdays.filter(day => day == "Mon")...

    println("\n\nApplying sortBy() operation,")
    weekdays.sortBy(_(0)).foreach(day => print(day + " "))                  // weekdays.sortBy(day => day(0))...

    println("\n\nApplying partition() operation,")

    println(weekdays.partition(_ == "Mon"))                                 // weekdays.partition(day => day == "Mon")

    // 2. Scan, Fold, Reduce
    println("\nApplying scanRight() operation,")
    val someNumbers = List(10, 20, 30, 40, 50, 60)
    someNumbers.scanRight(0)((n1, n2) => n1 - n2).foreach(num => print(num + " "))              // takes an initial value 0 and the difference operation

    println("\n\nApplying scanLeft() operation (Month on month cumulative student counts),")
    val janToMayStudentCount = List(3, 3, 2, 0, 3)
    val monthOnMonthCumStudentCount = janToMayStudentCount.scanLeft(20)((n1, n2) => n1 + n2)    // takes an initial value 20 and the sum operation
    monthOnMonthCumStudentCount.foreach(count => print(count + " "))
    println("\nTotal sum of students from Jan'19 through May'19 = " + monthOnMonthCumStudentCount(monthOnMonthCumStudentCount.length - 1))

    println("\nApplying foldRight() operation, " + someNumbers.foldRight(0)(_ - _))             // takes an initial value 0 and the difference operation

    println("\nApplying foldLeft() operation (Month on month cumulative student counts),")
    val cumStudentCount = janToMayStudentCount.foldLeft(20)(_ + _)                              // takes an initial value 20 and the sum operation
    println("Total sum of students from Jan'19 through May'19 = " + cumStudentCount)

    println("\nApplying reduceRight() operation, " + someNumbers.reduceRight(_ - _))            // takes no initial value and the difference operation

    println("\nApplying reduceLeft() operation (Month on month cumulative student counts),")
    val againJanToMayStudentCount = List(7, 7, 6, 4, 7)
    val againCumStudentCount = againJanToMayStudentCount.reduceLeft(_ + _)                      // takes no initial value and the sum operation
    println("Total sum of students from Jan'19 through May'19 = " + againCumStudentCount)

    println("\nApplying scan() operation: " + janToMayStudentCount.scan(20)(_ + _))
    println("\nApplying fold() operation: " + janToMayStudentCount.fold(20)(_ + _))
    println("\nApplying reduce() operation: " + againJanToMayStudentCount.reduce(_ + _))

  }
}
