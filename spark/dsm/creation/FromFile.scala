package com.dsm.creation

import com.dsm.model.Person
import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.SparkSession

/**
 * Created by Sidharth on 27/01/17.
 */
object FromFile {

//  val peopleJsonPath = "src/main/resources/people.json"

  // Create dataframe from json file
 // val peopleJsonPath = s"s3n://${s3Config.getString("s3_bucket")}/people.json"
//  val peopleDf = sparkSession.read.json(peopleJsonPath)

  def main(args: Array[String]) {
    val sparkSession = SparkSession.builder().master("local[*]").appName("Dataset creation").getOrCreate()

    val rootConfig = ConfigFactory.load("application.conf").getConfig("conf")
    val s3Config = rootConfig.getConfig("s3_conf")

    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", s3Config.getString("access_key"))
    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", s3Config.getString("secret_access_key"))

    // Create dataframe from json file
    val peopleJsonPath = s"s3n://${s3Config.getString("s3_bucket")}/people.json"
   // val peopleDf = sparkSession.read.json(peopleJsonPath)

   // peopleDf.filter("age > 20").show()

    // DataFrames can be converted to a Dataset by providing a class. Mapping will be done by name.
    import sparkSession.implicits._
   val peopleDS = sparkSession.read.json(peopleJsonPath).as[Person]
    peopleDS.filter(person => person.age > 20).show()

    sparkSession.stop()
  }

}
