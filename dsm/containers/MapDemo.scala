package com.dsm.containers

/**
  * Created by sidhartha.ray on 14-11-2017.
  */
object MapDemo {
  def main(args: Array[String]): Unit = {
    val statesCode = Map(
      "Karnataka" -> "KA",
      "Odisha" -> "OD",
      ("Andhra Pradesh", "AP")
    )
    println("statesCode = " + statesCode)
    println("Retrieve element from map using key,")
    println("Odisha's code = " + statesCode("Odisha"))
    //println("Telangana's code = " + statesCode("Telangana"))    // java.util.NoSuchElementException: key not found: Telangana
    println("Does Telangana's code exists in the map? " + statesCode.contains("Telangana"))

    println("\nIterating elements of map,")
    statesCode.foreach(pair => println(pair._1 + " = " + pair._2))

    println("\nIterating elements of map,")
    val states = List("Karnataka", "Odisha", "Andhra Pradesh")
    val codes = List("KA", "OD", "AP")

    println("\nCreating map using zip function object and iterating elements of map,")
    val stateCodesAgain = (states zip codes).toMap
    stateCodesAgain.foreach(println)
    println("Keyset = " + stateCodesAgain.keySet.toList)
    println("Values = " + stateCodesAgain.values.toList)

  }
}
