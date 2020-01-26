package com.dsm.containers

/**
  * Created by sidhartha.ray on 14-11-2017.
  */
object TupleDemo {
  def main(args: Array[String]): Unit = {
    // Light weight containers of hetereogenous type of elements (upto 22 elements)
    val personalInfo = ("Sidhartha", "Ray", 31, "M")
    println("personalInfo  = " + personalInfo)
    println("personalInfo size = " + personalInfo.productArity)
    println("personalInfo size = " + personalInfo._1)

    // Tuple containing 2 elements are also called as pairs
    val genderPair = ("Sidhartha" -> "M")  // ("Sidhartha", 31)
    println("genderPair = " + genderPair)
    // Iterating over a tuple
    personalInfo.productIterator.foreach(i => println("Value = " + i))
  //  personalInfo.productIterator.foreach(i => println( i))

    val (firstName, lastName, age, gender) = personalInfo
    println("First name = " + firstName)
    println("Last name = " + lastName)
    println("Age = " + age)
    println("Gender = " + gender)

    println(personalInfo._1)
    println(personalInfo._2)
    println(personalInfo._3)
    println(personalInfo._4)

   (printPersonGender _).tupled(genderPair)
  }

  def printPersonGender(name: String, gender: String): Unit = {
    println(s"Name:$name M/F:$gender")
  }
}
