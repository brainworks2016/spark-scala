package com.dsm.dataframe.limitations

import com.dsm.utils.Constants
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{BooleanType, StructField, StructType}
import org.apache.spark.sql.Row

object CompileTimeSafetyDemo2 {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession
      .builder
      .master("local[*]")
      .appName("Compile-time safety - Dataframe")
      .getOrCreate()
    sparkSession.sparkContext.setLogLevel(Constants.ERROR)

    val schema = StructType(List(StructField("test", BooleanType, true)))
    val rdd = sparkSession.sparkContext.parallelize(List(Row("0"), Row(true), Row("stuff")))
    val dataframe = sparkSession.createDataFrame(rdd, schema)

    dataframe.show()

    sparkSession.close()
  }
}
