package  com.dsm.oops

object CaseClassDemo {

  def main(args: Array[String]): Unit = {
    val me = Person("Sidhartha", "Ray")             // No 'new' keyword here
    println(me)                                     // but we haven't implemented it's toString() function

    val dsm = BigDataInstitute("Data Science Monks", "Bangalore", "Spark with Scala & AWS")
    println(getInstituteDetails(dsm))
  }

  def getInstituteDetails[T <: Institute] (inst: T): String = inst match {
    case BigDataInstitute(name: String, location: String, program: String) =>
      s"Institute($name,$location,$program)"
      // statement 1
      // statement 1
      // statement 1
    case inst: Institute => {inst.getDetails()}
  }
}

// Implements equality (hashCode & equals), toString along with Serializable
case class Person(firstName: String, lastName: String)

abstract class Institute (name: String, location: String) {
  def getDetails(): String = {
    s"Institute($name,$location)"
  }
}

case class BigDataInstitute(name: String, location: String, program: String) extends Institute(name: String, location: String)

