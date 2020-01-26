package com.dsm.creation

import org.apache.spark.sql.SparkSession

/**
 * Created by Sidharth on 27/01/17.
 */
object FromCollections {

  def main(args: Array[String]) {
    val sparkSession = SparkSession.builder().master("local[*]").appName("Dataset creation").getOrCreate()

    import sparkSession.implicits._
    val ds = Seq(1, 2, 3).toDS()
    ds.map(rec => rec + 1).foreach(rec => println(rec))

    sparkSession.stop()
  }
}
