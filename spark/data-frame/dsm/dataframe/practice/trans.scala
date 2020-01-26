package com.dsm.dataframe.practice

import com.dsm.utils.Constants
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._


object trans {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder.master("local[2]").appName("Dataframe Example").getOrCreate()
    sparkSession.sparkContext.setLogLevel(Constants.ERROR)
    import sparkSession.implicits._
    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", Constants.ACCESS_KEY)
    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", Constants.SECRET_ACCESS_KEY)


    println("\nCreating dataframe from CSV file using 'SparkSession.read.format()',")
    val tranSchema = new StructType()
      .add("TransactionTimeStamp", StringType,true)
      .add("value", StringType,true)

    val tranDf = sparkSession.read
      .option("header", "false")
      .option("delimiter", ",")
      .format("csv")
      .schema(tranSchema)
      .load("s3n://" + Constants.S3_BUCKET + "/tran.csv")

    tranDf.printSchema()
    tranDf.show()
   val tranDf1 = tranDf
     .withColumn("created_time_ist", unix_timestamp(tranDf("TransactionTimeStamp"), "DD-MM-yyyy HH:mm:ss").cast(TimestampType))
    tranDf1.printSchema()
    tranDf1.show()

    val tranDf2 =tranDf1
    .withColumn("dateColumn", tranDf1("created_time_ist").cast(DateType))
    tranDf2.printSchema()
    tranDf2.show()

  val tranDf3 =  tranDf2
      .select(col("dateColumn"),col("value"),
        when($"value" === "NULL", "1")
          .otherwise("0").as("FailedTran"))

   val tranDf4= tranDf3
      .select(col("*"),
        when($"value" === "NULL", "0")
          .otherwise($"value") .as("Amount"))
    tranDf4.show()

    val tranDf5 = tranDf4.select(col("dateColumn"),col("Amount"),col("FailedTran"))
    tranDf5.show()
    val tranDf6 = tranDf5
      .groupBy($"dateColumn")
       .agg(sum($"Amount").cast(IntegerType).as("TotalTransaction"),
        sum($"FailedTran").cast(IntegerType).as("FailedTranscation")
       )

    tranDf6.show()



    sparkSession.close()
      }
}
