package com.dsm.containers

import scala.collection.mutable


object MutableCollections {
  def main(args: Array[String]): Unit = {

    println("Mutable list below,")
    val daysList = mutable.Buffer("Mon", "Tue", "Wed", "Thus", "Fri")
    daysList.foreach(day => print(day + " "))

    println("\n\nAdding element 'Sat' to a list,")
    daysList += "Sat"
    daysList.foreach(day => print(day + " "))

    println("\n\nRemoving element 'Mon' from list,")
    daysList -= "Mon"
    daysList.foreach(day => print(day + " "))

    println("\n\nMutable set below,")
    val daysSet = mutable.Set("Mon", "Tue", "Wed", "Thus", "Fri")
    daysSet.foreach(day => print(day + " "))
    println("\n\nAdding element 'Sat' to a set,")
    daysSet += "Sat"
    daysSet.foreach(day => print(day + " "))

    println("\n\nRemoving element 'Mon' from set,")
    daysSet -= "Mon"
    daysSet.foreach(day => print(day + " "))

    println("\n\nMutable map below,")
    val statesCode = mutable.Map(
      "Karnataka" -> "KA",
      "Odisha" -> "OD",
      ("Andhra Pradesh", "AP")
    )
    statesCode.foreach(state => print(state + " "))

    println("\n\nAdding elements to a map,")
    statesCode += ("Telangana" -> "TS")
    statesCode("Maharastra") = "MH"
    statesCode.foreach(state => print(state + " "))

    println("\n\nRemoving elements from a map,")
    statesCode -= "Andhra Pradesh"
    statesCode.foreach(state => print(state + " "))

  }
}