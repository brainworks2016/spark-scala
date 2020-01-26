package com.dsm.practice

import com.dsm.model.Person1
import com.dsm.utils.Constants
import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object MoreFundtionDemo {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession
      .builder
      .master("local[*]")
      .appName("Dataframe Example")
      .getOrCreate()
    sparkSession.sparkContext.setLogLevel(Constants.ERROR)
    import sparkSession.implicits._

    val rootConfig = ConfigFactory.load("application.conf").getConfig("conf")
    val s3Config = rootConfig.getConfig("s3_conf")

    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", s3Config.getString("access_key"))
    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", s3Config.getString("secret_access_key"))

    val peopleDs = List(
      Person1("Sidhartha", "Ray", 32, None, Some("Programmer")),
      Person1("Pratik", "Solanki", 22, Some(176.7), None),
      Person1("Ashok ", "Pradhan", 62, None, None),
      Person1(" ashok", "Pradhan", 42, Some(125.3), Some("Chemical Engineer")),
      Person1("Pratik", "Solanki", 22, Some(222.2), Some("Teacher"))
    ).toDS()

    peopleDs.show()
    peopleDs.groupBy($"firstName").agg(first($"weightInLbs")).show()
    peopleDs.groupBy(trim(lower($"firstName"))).agg(first($"weightInLbs")).show()
    peopleDs.groupBy(trim(lower($"firstName"))).agg(first($"weightInLbs", true)).show()
    peopleDs.sort($"weightInLbs".desc).groupBy(trim(lower($"firstName"))).agg(first($"weightInLbs", true)).show()
    peopleDs.sort($"weightInLbs".asc_nulls_last).groupBy(trim(lower($"firstName"))).agg(first($"weightInLbs", true)).show()

    var correctedPeopleDs = peopleDs
      .withColumn("firstName", initcap($"firstName"))
      .withColumn("firstName", ltrim(initcap($"firstName")))
      .withColumn("firstName", trim(initcap($"firstName")))
    correctedPeopleDs.groupBy($"firstName").agg(first($"weightInLbs")).show()

    var correctedPeopleDs1 = correctedPeopleDs.withColumn("fullName", format_string("%s %s", $"firstName", $"lastName"))
    correctedPeopleDs1.show()

    var correctedPeopleDs2 = correctedPeopleDs1
      .withColumn("weightInLbs", coalesce($"weightInLbs", lit(0)))
    correctedPeopleDs2.show()

    correctedPeopleDs2
      .filter(lower($"jobType").contains("engineer"))
      .show()

    correctedPeopleDs2
      .filter(lower($"jobType").isin(List("chemical engineer", "teacher"):_*))
      .show()

    sparkSession.close()
  }
}
