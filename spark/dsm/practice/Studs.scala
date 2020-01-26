package com.dsm.practice

import com.dsm.utils.Constants
import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.SparkSession


object Studs {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder().master("local[*]").appName("Dataset creation").getOrCreate()
    sparkSession.sparkContext.setLogLevel(Constants.ERROR)
    val rootConfig = ConfigFactory.load("application.conf").getConfig("conf")
    val s3Config = rootConfig.getConfig("s3_conf")

    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", s3Config.getString("access_key"))
    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", s3Config.getString("secret_access_key"))

  //  val csvPath = s"s3n://${s3Config.getString("s3_bucket")}/stud.csv"


   // val StudDS = sparkSession.read.csv(csvPath).as[Stud]

  //  StudDS.show()


sparkSession.close()

}
}
