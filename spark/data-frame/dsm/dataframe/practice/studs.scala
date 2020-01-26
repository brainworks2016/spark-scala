package com.dsm.dataframe.practice

import com.dsm.utils.Constants
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{row_number, _}
import org.apache.spark.sql.types._


object studs {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder.master("local[2]").appName("Dataframe Example").getOrCreate()
    sparkSession.sparkContext.setLogLevel(Constants.ERROR)
    import sparkSession.implicits._


    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", Constants.ACCESS_KEY)
    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", Constants.SECRET_ACCESS_KEY)


    println("\nCreating dataframe from CSV file using 'SparkSession.read.format()',")
    val studSchema = new StructType()
      .add("id", IntegerType,true)
      .add("month", StringType,true)
      .add("score", IntegerType,true)

    val studDf = sparkSession.read
      .option("header", "false")
      .option("delimiter", ",")
      .format("csv")
      .schema(studSchema)
      .load("s3n://" + Constants.S3_BUCKET + "/stud.csv")

    studDf.printSchema()
    studDf.show()

    val scoreWindow = Window.partitionBy($"id")
      .orderBy($"score")
  val studDfWindow =   studDf.select(col("id"),col("month"),col("score")
    ,row_number().over(scoreWindow).as("row_number")
    )

    studDfWindow.show()



    val studDfWindow1 = studDfWindow
      .select(col("id"),col("month"),col("score"))
      .withColumnRenamed("score", "2nd Highest Score")
        .where(col("row_number")===2)

        studDfWindow1.show()
    sparkSession.close()

  }
}
