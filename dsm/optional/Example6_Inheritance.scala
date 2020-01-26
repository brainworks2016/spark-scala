package com.dsm.oops

object Example6_Inheritance {

  def main(args: Array[String]): Unit = {
    val reader:Reader = new Reader
    reader.name = "Hari"
    reader.subject = "Physics"
    reader.show()

    val prof:Professor = new Professor
    prof.name = "Shankar"
    prof.subject = "Physics"
    prof.specialization = "Particle Physics"
    prof.show()


  }
}

trait Staff {
  var name:String = null
  val grade:Int = 5

  def signIn (): Unit = {
    println(name + " singned in.")
  }

  def show()
}

class Address {
  var addressLine1:String = null
  var addressLine2:String = null
  var pin:Int = 0
}

class Reader extends Staff {
  var subject:String = null
  override val grade: Int = 4

  class RegionalOffice extends Address {
    var region:String = null
  }

  override def show() = {
    println("Reader[Rdr. " + name + ", " + subject + ", " + grade + "]")
  }

  def showAddress(address: Address): Unit = {
    println("Reader's address:")
    println("Address Line1: "  + address.addressLine1)
    println("Address Line2: "  + address.addressLine2)
    println("PIN: "  + address.pin)
  }
}

class Professor extends Reader {
  var specialization:String = null
  override val grade: Int = 3

  override def show() = {
    println("Professor[Prof. " + name + ", " + subject + ", " + specialization + ", " + grade + "]")
  }

}


