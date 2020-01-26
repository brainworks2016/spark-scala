package com.dsm.containers

import com.dsm.model.Equity

object SetOfUserDefinedType {
  def main(args: Array[String]): Unit = {
    var setA = Set(
      new Equity("REL", 905),
      new Equity("MARUTI SUJUKI", 8010),
      new Equity("ASHOK LEYLAND", 112))
    println("Set A: " + setA)

    // Set properties
    println("Head: " + setA.head)
    println("Tail: " + setA.tail)
    println("Is the set empty? " + setA.isEmpty)

    // Merging 2 sets
    var setB = Set(
      new Equity("SUZLON ENERGY", 71),
      new Equity("REL", 905))
    var setAnB = setA ++ setB
    println("# of elements in setA: " + setA.size)
    println("# of elements in setB: " + setB.size)
    println("# of elements in set A and B: " + setAnB.size)
    println("Set A and B: " + setAnB)

    // Adding and removing elements in Set
    setAnB += new Equity("UPL", 700.5)
    println("Adding 'UPL' to allGames: " + setAnB)
    setAnB += new Equity("DHAMPUR SUGAR", 74)
    println("Adding 'DHAMPUR SUGAR' to allStocks: " + setAnB)
    setAnB -= new Equity("ASHOK LEYLAND", 112)
    println("Removing 'ASHOK LEYLAND' from allStocks: " + setAnB)

    // Set operations
    var commonStocks = setA.intersect(setB)
    println("Set intersection: " + commonStocks)
    var allStocks2 = setA.union(setB)
    println("Set union: " + allStocks2)
    var onlyStocks = setA.diff(setB)
    println("Set difference: " + onlyStocks)
  }
}