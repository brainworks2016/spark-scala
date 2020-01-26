package com.dsm.typed.operation

import com.dsm.utils.Constants
import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.SparkSession

/**
 * Created by Sidharth on 27/01/17.
 */
object Operations {



  def main(args: Array[String]) {
    val sparkSession = SparkSession.builder().master("local[*]").appName("RDD Comparision").getOrCreate()
    sparkSession.sparkContext.setLogLevel(Constants.ERROR)

    val rootConfig = ConfigFactory.load("application.conf").getConfig("conf")
    val s3Config = rootConfig.getConfig("s3_conf")

    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", s3Config.getString("access_key"))
    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", s3Config.getString("secret_access_key"))

    val peopleFilePath = s"s3n://${s3Config.getString("s3_bucket")}/people.csv"
    val linesRdd = sparkSession.sparkContext.textFile(peopleFilePath)
    val wordsRdd = linesRdd
      .filter(line => line != "")
      .map(line => line.split(","))
      .map(rec => (rec(0), rec(1)))
    println("RDD contents")
    wordsRdd.foreach(println)

    import sparkSession.implicits._
    val linesDS = sparkSession.read.text(peopleFilePath).as[String]
    val wordsDS = linesDS
      .filter(line => line != "")
      .map(line => line.split(","))
      .map(rec => (rec(0), rec(1)))
    println("Dataset contents")
    wordsDS.show()

    sparkSession.stop()
  }

}
