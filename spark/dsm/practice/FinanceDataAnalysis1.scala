package com.dsm.practice

import com.dsm.model.Company
import com.dsm.utils.Constants
import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.{Encoders, SparkSession}

object FinanceDataAnalysis1 {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession
      .builder
      .master("local[*]")
      .appName("Dataset Demo")
      .getOrCreate()
    sparkSession.sparkContext.setLogLevel(Constants.ERROR)
    val rootConfig = ConfigFactory.load("application.conf").getConfig("conf")
    val s3Config = rootConfig.getConfig("s3_conf")

    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", s3Config.getString("access_key"))
    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", s3Config.getString("secret_access_key"))
/*
    case class Account(number: String, firstName: String, lastName: String)

    val finFilePath = s"s3n://${s3Config.getString("s3_bucket")}/finances-small"
    implicit val txnEncoder = Encoders.product[Transaction]
    val financeDs = sparkSession.read
      .parquet(finFilePath)
     // .withColumn("date", to_date(unix_timestamp($"Date", "MM/dd/yyyy").cast("timestamp")))
      .as[Transaction]

    financeDs.printSchema()
    financeDs.show(3, false)

    financeDs.orderBy("Amount").show(5)

    financeDs
       .select(concat_ws(" - ", col("AccountNumber"),col("Description")).alias("AccountDetails"))
      .show(5, false)

    val aggFinanceDs = financeDs
      .groupBy(col("AccountNumber"))
      .agg(avg(col("Amount")).as("AverageTransaction"),
        sum(col("Amount")).as("TotalTransaction"),
        count(col("Amount")).as("NumberOfTransaction"),
        max(col("Amount")).as("MaxTransaction"),
        min(col("Amount")).as("MinTransaction"),
        collect_set("Description").as("UniqueTransactionDescriptions")
      )
    aggFinanceDs.show(false)

    aggFinanceDs.select(col("AccountNumber"),col("UniqueTransactionDescriptions"),
      size(col("UniqueTransactionDescriptions")).as("CountOfUniqueTransactionTypes"),
      array_contains(col("UniqueTransactionDescriptions"),"Movies").as("WentToMovies")
    ).show(truncate = false)*/

    val companiesJson = List(
      """{"company":"NewCo","employees":[{"firstName":"Sidhartha","lastName":"Ray"},{"firstName":"Pratik","lastName":"Solanki"}]}""",
      """{"company":"FamilyCo","employees":[{"firstName":"Jiten","lastName":"Pupta"},{"firstName":"Pallavi","lastName":"Gupta"}]}""",
      """{"company":"OldCo","employees":[{"firstName":"Vivek","lastName":"Garg"},{"firstName":"Nitin","lastName":"Gupta"}]}""",
      """{"company":"ClosedCo","employees":[]}"""
    )
    val companiesRDD = sparkSession.sparkContext.makeRDD(companiesJson)

  //  val ds =  sparkSession.createDataset(companiesRDD)
    implicit val tnEncoder = Encoders.product[Company]
    val companiesDs = sparkSession.read.json(companiesRDD).as[Company]
    companiesDs.show(false)
//    companiesDs.printSchema()


    sparkSession.close()
  }
}
