package com.dsm.dataframe.practice

import com.dsm.utils.Constants
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._


object prod {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder.master("local[2]").appName("Dataframe Example").getOrCreate()
    sparkSession.sparkContext.setLogLevel(Constants.ERROR)
    import sparkSession.implicits._

    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", Constants.ACCESS_KEY)
    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", Constants.SECRET_ACCESS_KEY)


    println("\nCreating dataframe from CSV file using 'SparkSession.read.format()',")

    val tranDf = sparkSession.read
      .option("header", "true")
     // .option("delimiter", ",")
      .format("csv")
      .option("inferschema" ,"true")
      .load("s3n://" + Constants.S3_BUCKET + "/product.csv")
    tranDf.printSchema()
    tranDf.show()


  val tranDf1 =   tranDf.groupBy("prod")
        .pivot("month")
      .agg(avg($"sale"))
      .orderBy("prod")
    tranDf1.show()

    val tranDf2 =  tranDf1.withColumn("201901",col("201901").cast("Int"))
        .withColumn("201902",col("201902").cast("Int"))
      .withColumn("201903",col("201903").cast("Int"))
    tranDf2.show()



    sparkSession.close()
  }
}