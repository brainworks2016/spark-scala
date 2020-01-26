package com.dsm.assignments

object WordList {
  def main(args: Array[String]): Unit = {

    val list = List(
      "Hello World",
      "Hello",
      "Hello World"
    )

  val lines =  list.flatMap(line => line.split(","))
    println(" sentences : " +  lines )
    val linesfreq = lines.groupBy(line => line)
    println(linesfreq)
    val groupedData = linesfreq.mapValues(wordList => wordList.length)
    println(groupedData)
    groupedData.foreach(word => println(word))

 /*   list.map(line => line.split(" ")).flatten
      .groupBy(word => word)
      .mapValues(wordList => wordList.length)
      .foreach(word => println(word)) */
    // (Hello,3)
    // (World,2)

    // (Hello World, 2)
    // (Hello World, 2)
    // (Hello, 1)

  }
}