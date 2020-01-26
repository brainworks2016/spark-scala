package com.dsm.dataframe.practice

import com.dsm.utils.Constants
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._


object tranSql {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder.master("local[2]").appName("Dataframe Example").getOrCreate()
    sparkSession.sparkContext.setLogLevel(Constants.ERROR)
    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", Constants.ACCESS_KEY)
    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", Constants.SECRET_ACCESS_KEY)


    println("\nCreating dataframe from CSV file using 'SparkSession.read.format()',")
    val tranSchema = new StructType()
      .add("TransactionTimeStamp", StringType, true)
      .add("value", StringType, true)

    val tranDf = sparkSession.read
      .option("header", "false")
      .option("delimiter", ",")
      .format("csv")
      .schema(tranSchema)
      .load("s3n://" + Constants.S3_BUCKET + "/tran.csv")



    tranDf.createOrReplaceTempView("raw_tran")
    sparkSession.sql(
      """
      select
        TransactionTimeStamp,
         to_date(cast(unix_timestamp(TransactionTimeStamp, 'dd-MM-yyyy hh:mm:ss') as timestamp)) as Date,
        cast(value as int)
      from
        raw_tran
      """)
      .createOrReplaceTempView("transaction")

    tranDf.printSchema()
    sparkSession.sql("""
       select Date,sum(Amount) Total_Amount,sum(fail) Fail_Transaction from (
      select
        Date,
         CASE WHEN value IS NULL THEN 0 ELSE value END as Amount,
        CASE WHEN value IS NULL THEN 1 ELSE 0 END as fail
        from
        transaction ) group by Date
      """)
        .show()

    sparkSession.close()
  }
}