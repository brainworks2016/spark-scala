package com.dsm.intro.to.dataset

import java.text.SimpleDateFormat
import java.util.Calendar

import com.dsm.model.Employee
import com.dsm.utils.Constants
import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.SparkSession

object FuncProgApi {

  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession
      .builder
      .master("local[*]")
      .appName("Compile-time safety - Dataframe")
      .getOrCreate()
    sparkSession.sparkContext.setLogLevel(Constants.ERROR)

    val rootConfig = ConfigFactory.load("application.conf").getConfig("conf")
    val s3Config = rootConfig.getConfig("s3_conf")

    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", s3Config.getString("access_key"))
    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", s3Config.getString("secret_access_key"))

    // Create dataset
    import sparkSession.implicits._
    val empCsvPath = s"s3n://${s3Config.getString("s3_bucket")}/employee"
    val employeeDs = sparkSession.read.csv(empCsvPath).toDF("name", "dateOfBirth").as[Employee]

    employeeDs.printSchema()

    // Calculating age of each employee in the dataframe
    employeeDs.map{emp =>
      val birthDay = Calendar.getInstance()
      birthDay.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(emp.dateOfBirth))
      val today = Calendar.getInstance()
      emp.age = today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR)
      emp
      }
      .collect()
      .foreach(println)

    sparkSession.stop()
  }

}
