package com.dsm.practice

import com.dsm.model.Person1
import com.dsm.utils.Constants
import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.SparkSession

object MoreFunDemoSql {
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
    println("\nCreating dataset SQL")
    peopleDs.createOrReplaceTempView("people")
    sparkSession.sql("""
      select* from people
      """)
      .show()
    sparkSession.sql("""
      select firstName,first(weightInLbs) from people group by firstName
      """)
      .show()
    sparkSession.sql("""
      select trim(lower(firstName)),first(weightInLbs) from people group by trim(lower(firstName))
      """)
      .show()
    sparkSession.sql("""
    select firstName ,weightInLbs from (
      select trim(lower(firstName)) firstName,weightInLbs
      ,row_number() over (partition by trim(lower(firstName)) order by weightInLbs asc NULLS LAST) as row_number
      from people ) where row_number = 1
      """).show()
    sparkSession.sql("""
      select trim(lower(firstName)),last(weightInLbs) from people group by trim(lower(firstName))
      """).show()
    sparkSession.sql("""
      select trim(initcap(firstName)),first(weightInLbs) from people group by trim(initcap(firstName))
      """).show()

    sparkSession.sql("""
      select trim(initcap(firstName)),lastName,age,weightInLbs,jobType ,CONCAT(trim(initcap(firstName)), ' ' , lastName) fullName
       from people
      """).show()
    sparkSession.sql("""
      select trim(initcap(firstName)),lastName,age,nvl(weightInLbs,0) weightInLbs,jobType
      ,CONCAT(trim(initcap(firstName)), ' ' , lastName) fullName  from people
      """).show()
    sparkSession.sql("""
      select trim(initcap(firstName)),lastName,age,weightInLbs,jobType
      ,CONCAT(trim(initcap(firstName)), ' ' , lastName) fullName  from people where instr(jobType, 'Engineer') <> 0
      """).show()
    sparkSession.sql("""
      select trim(initcap(firstName)),lastName,age,weightInLbs,jobType
      ,CONCAT(trim(initcap(firstName)), ' ' , lastName) fullName  from people where jobType in ('Chemical Engineer','Teacher')
      """).show()

    sparkSession.close()
  }
}
