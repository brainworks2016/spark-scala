package com.dsm.optional

import java.io.{File, FileOutputStream, FileWriter, PrintWriter}

import com.dsm.model.Stock

object FileWriterDemo {
  def main(args: Array[String]): Unit = {
    val stocks = List(
      new Stock("ASHOK LEYLAND", 112),
      new Stock("REL CAPITAL", 490),
      new Stock("MEGH", 111),
      new Stock("UNITECH", 5)
    )

    val out = new FileWriter("E:\\AIMIA\\workspace\\scala-examples\\src\\main\\resources\\Mid_Cap_Stocks.csv")
    out.write("symbol,price\n")
    for(stock <- stocks) {
      if(stock.price > 100)
        out.write(stock.symbol + "," + stock.price + "\n")
    }
    out.close()
  }
}