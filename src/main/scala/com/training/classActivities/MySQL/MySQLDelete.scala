package com.training.classActivities.MySQL

import com.training.classActivities.MySQL.MySQLInsert.connection

import java.sql.{Connection, DriverManager, PreparedStatement}

object MySQLDelete extends App {
  val driver = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://localhost:3306/movies"
  val username = "root"
  val password = "root"
  // connection instance creation
  var connection: Connection = null
  try {
    Class.forName(driver)
    connection = DriverManager.getConnection(url, username, password)
    val insertMySQL = """delete from movies.movies where title = ?; """
    val preparedStatement: PreparedStatement = connection.prepareStatement(insertMySQL)
    preparedStatement.setString(1, "ABC")
    preparedStatement.execute()
  } catch {
    case e: Exception => e.printStackTrace()
  }
  finally {
    connection.close()
  }
}
