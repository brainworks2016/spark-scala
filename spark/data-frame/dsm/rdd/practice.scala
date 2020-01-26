package com.dsm.rdd

import org.apache.spark.sql.SparkSession

/**
  * Count: Swiss students who have debt & financial dependents:
  * 1. Inner join first
  * 2. Filter to select people in Switzerland
  * 3. Filter to select people with debt & financial dependents
  *
  */
object practice {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder.master("local[*]").appName("Dataframe Example").getOrCreate()
    sparkSession.sparkContext.setLogLevel("ERROR")

    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", "AKIA4RA4DVP6KFWBKYM3")
    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", "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")

    val demographicsRDD = sparkSession.sparkContext.textFile("s3n://madhu12345/demographic.csv")
    val financesRDD = sparkSession.sparkContext.textFile("s3n://madhu12345/finances.csv")
    val coursesRDD = sparkSession.sparkContext.textFile("s3n://madhu12345/course.csv")

    //    println("# of records = " + demographicsRDD.count())

    val demographicsPairedRdd = demographicsRDD.map(record => record.split(","))
println("print :" + demographicsPairedRdd)
  }
}
