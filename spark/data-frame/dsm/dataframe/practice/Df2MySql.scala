package com.dsm.dataframe.practice

import com.dsm.utils.Constants
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.sql.{SaveMode, SparkSession}

object Df2MySql {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder.master("local[*]").appName("Dataframe Example").getOrCreate()
    sparkSession.sparkContext.setLogLevel(Constants.ERROR)

    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", Constants.ACCESS_KEY)
    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", Constants.SECRET_ACCESS_KEY)

    val rootConfig = ConfigFactory.load("application.conf").getConfig("conf")
    val mysqlConfig = rootConfig.getConfig("mysql_conf")

    val studDf = sparkSession.read
      .option("header", "false")
      .option("delimiter", ",")
      .option("inferSchema", "true")
      .csv("s3n://" + Constants.S3_BUCKET + "/stud.csv")
      .toDF("id", "month", "mark")

    studDf.printSchema()
    studDf.show()



    var jdbcParams = Map("url" ->  getMysqlJdbcUrl(mysqlConfig),
      "lowerBound" -> "1",
      "upperBound" -> "100",
      "dbtable" -> "testdb.STUDENT",
      "numPartitions" -> "2",
      "partitionColumn" -> "Id",
      "user" -> mysqlConfig.getString("username"),
      "password" -> mysqlConfig.getString("password")
    )

    println("\nReading data from DF to MYSQL")
    studDf.write.format("jdbc")
      .option("driver", "com.mysql.cj.jdbc.Driver")
      .options(jdbcParams)
      .mode(SaveMode.Overwrite)
      .save()

    sparkSession.stop()
  }

  // Creating Redshift JDBC URL
  def getMysqlJdbcUrl(mysqlConfig: Config): String = {
    val host = mysqlConfig.getString("hostname")
    val port = mysqlConfig.getString("port")
    val database = mysqlConfig.getString("database")
    s"jdbc:mysql://$host:$port/$database?autoReconnect=true&useSSL=false"
  }

}