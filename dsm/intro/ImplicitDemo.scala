package com.dsm.intro

object ImplicitDemo {
  def main(args: Array[String]): Unit = {

    // Should be in scope where we use this
    implicit class StringUtils (myString: String) {
      def scalaWordCount() = {
        val splitedWords = myString.split(" ")
        val groupedWords = splitedWords.groupBy(word => word)
        val wordCounts = groupedWords.mapValues(group => group.size)
        wordCounts
      }
    }

    val wordCount = "Spark Collections mimic Scala Collections".scalaWordCount()
    println(wordCount)

  }
}

