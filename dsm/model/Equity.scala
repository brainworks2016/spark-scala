package com.dsm.model

class Equity(val symbol: String, val price: Double) {
  override def hashCode = 41 + (41 * symbol.hashCode) + (41 * price.toInt)

  override def equals(other: Any) = other match {
    case that: Equity => this.symbol == that.symbol && this.price == that.price
    case _ => false
  }

  override def toString: String = "Equity[" + symbol + ", " + price + "]"
}