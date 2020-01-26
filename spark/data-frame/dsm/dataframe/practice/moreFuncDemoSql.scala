package com.dsm.dataframe.practice

import com.dsm.model.Person
import com.dsm.utils.Constants
import org.apache.spark.sql.SparkSession

object moreFuncDemoSql {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder.master("local[2]").appName("Dataframe Example").getOrCreate()
    sparkSession.sparkContext.setLogLevel(Constants.ERROR)


    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", Constants.ACCESS_KEY)
    sparkSession.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", Constants.SECRET_ACCESS_KEY)

    val peopleDf = sparkSession.createDataFrame(List(
      Person("Sidhartha", "Ray", 32, None, Some("Programmer")),
      Person("Pratik", "Solanki", 22, Some(176.7), None),
      Person("Ashok ", "Pradhan", 62, None, None),
      Person(" ashok", "Pradhan", 42, Some(125.3), Some("Chemical Engineer")),
      Person("Pratik", "Solanki", 22, Some(222.2), Some("Teacher"))
    ))


    println("\nCreating dataframe ")

    peopleDf.printSchema()
   // peopleDf.show()
    println("\nCreating dataframe SQL")
    peopleDf.createOrReplaceTempView("people")
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
