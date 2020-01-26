package com.dsm.assignments

object NumList {
  def main(args: Array[String]): Unit = {
    val numList = List(1, 2, 3, 1, 2, 4)

   println(" sentences : " + numList)

    val linesfreq = numList.groupBy(line => line)
    println(linesfreq)
    val groupedData = linesfreq.mapValues(wordList => wordList.length)
    println(groupedData)
    groupedData.foreach(word => println(word))

  }
}