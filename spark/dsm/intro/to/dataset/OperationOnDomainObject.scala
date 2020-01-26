package com.dsm.intro.to.dataset

import com.dsm.model.Person
import com.dsm.utils.Constants
import org.apache.spark.sql.SparkSession

object OperationOnDomainObject {
  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder().master("local[*]").appName("Comparision with Dataframe").getOrCreate()
    sparkSession.sparkContext.setLogLevel(Constants.ERROR)

    val persons = Seq(
      Person("Sidharth", 30),
      Person("Vinay", 26),
      Person("Piyush", 25)
    )

    // Create RDD of Person
    val personRdd = sparkSession.sparkContext.makeRDD(persons)
    personRdd.collect().foreach(println)
    println(personRdd.collect())

    // Create Dataset from an RDD[Person]
    import sparkSession.implicits._
    val personDs = sparkSession.createDataset(personRdd)
    personDs.show()

    // We get back RDD[Person]
    println(personDs.rdd.collect())

    sparkSession.stop()
  }
}