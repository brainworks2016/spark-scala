package com.dsm.dataframe.practice

import com.dsm.utils.Constants
import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.SparkSession

object Df2Sftp {
  def main(args: Array[String]): Unit = {
    try {
      val sparkSession = SparkSession.builder.master("local[*]").appName("Dataframe Example").getOrCreate()
      sparkSession.sparkContext.setLogLevel(Constants.ERROR)

      sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", Constants.ACCESS_KEY)
      sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", Constants.SECRET_ACCESS_KEY)

      val rootConfig = ConfigFactory.load("application.conf").getConfig("conf")
      val sftpConfig = rootConfig.getConfig("sftp_conf")


      val studDf = sparkSession.read
        .option("header", "false")
        .option("delimiter", ",")
        .option("inferSchema", "true")
        .csv("s3n://" + Constants.S3_BUCKET + "/stud.csv")
        .toDF("id", "month", "mark")

      studDf.printSchema()
      studDf.show()


      // val tabList = rootConfig.getStringList("table_list")
      studDf.write.
        format("com.springml.spark.sftp").
        option("host", sftpConfig.getString("hostname")).
        option("port", sftpConfig.getString("port")).
        option("username", sftpConfig.getString("username")).
        option("pem", sftpConfig.getString("pem")).
        option("fileType", "csv").
       option("delimiter", "|").
        save(s"${sftpConfig.getString("directory")}/stud11.csv")

      sparkSession.close()
    } catch {
      case ex: Throwable => {
        ex.printStackTrace()
      }
    }
  }
}
