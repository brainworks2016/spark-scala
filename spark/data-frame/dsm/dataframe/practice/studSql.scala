package com.dsm.dataframe.practice

import com.dsm.utils.Constants
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._


object studSql {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder.master("local[2]").appName("Dataframe Example").getOrCreate()
    sparkSession.sparkContext.setLogLevel(Constants.ERROR)


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

    studDf.createOrReplaceTempView("raw_student")
    sparkSession.sql("""
      select
        id,
        month,
        score,
        row_number() over (partition by id order by score desc) as row_number
      from
        raw_student
      """)
      .createOrReplaceTempView("student")


    studDf.printSchema()
    studDf.show()

    sparkSession.sql("""
      select
        id,
        month,
        score as 2nd_highest_score
        from
        student where row_number = 2
      """)
      .show()

    sparkSession.close()

  }
}
