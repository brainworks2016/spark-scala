package com.dsm.optional

import java.io._

import com.dsm.model.Stock

object ObjectSerializationDemo {
  def main(args: Array[String]): Unit = {
    // (1) create a Stock instance
    val rel = new Stock("REL", 905.5)

    // (2) write the instance out to a file
    val oos = new ObjectOutputStream(new FileOutputStream("E:\\AIMIA\\workspace\\scala-examples\\src\\main\\resources/REL.ser"))
    oos.writeObject(rel)
    oos.close
    println("Serializing " + rel)
  }
}
