package com.dsm.model

// Person2 domain object
case class Person2(name:String, age:String) {
  override def toString: String = s"Person($name,$age)"
}