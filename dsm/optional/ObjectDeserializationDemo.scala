package com.dsm.optional

import java.io._

import com.dsm.model.Stock

object ObjectDeserializationDemo {
  def main(args: Array[String]): Unit = {
    // (3) read the object back in
    val ois = new ObjectInputStream(new FileInputStream("E:\\AIMIA\\workspace\\scala-examples\\src\\main\\resources\\REL.ser"))
    //val obj = ois.readObject
    val stock = ois.readObject.asInstanceOf[Stock]
    ois.close

    // (4) print the object that was read back in
    println("Deserializing " + stock)
  }
}
