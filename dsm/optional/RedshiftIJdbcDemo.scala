package com.dsm.optional

import java.util.Properties

object RedshiftIJdbcDemo {
  def main(args: Array[String]): Unit = {
    // Creating DB connection
    Class.forName("com.amazon.redshift.jdbc.Driver")
    val props = new Properties()
    import java.sql.DriverManager
    props.setProperty("user", "master")
    props.setProperty("password", "Temp1234")
    val  conn = DriverManager.getConnection("jdbc:redshift://test.cihc4qdcty4q.eu-west-1.redshift.amazonaws.com:5439/testdb?", props)

    // Insert into Redshift table
    val statement = conn.createStatement()
    val recCount = statement.executeUpdate("INSERT INTO staging.checkpoint(table_name, status, number_records_inserted, time_stamp) values('staging.STG_OL_TRANSACTION_SYNC', 'S', 10, '2019-05-26 00:00:00')")
    println(recCount + " # of records inserted")


    // Query Redshift table
    var selectQuery = s"SELECT * FROM staging.checkpoint"
    val recordsSelected = statement.executeQuery(selectQuery)
    while (recordsSelected.next()) {
      println("Table name: " + recordsSelected.getString(1))
      println("Status: " + recordsSelected.getString(2))
      println("# of records: " + recordsSelected.getString(3))
      println("Insertion timestamp: " + recordsSelected.getTimestamp(4))
    }

  }
}
