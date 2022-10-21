package com.training.classActivities.MySQL

import java.sql.{Connection, DriverManager}

object MySQLTest extends App {
  val driver = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://localhost:3306/movies"
  val username = "root"
  val password = "root"
  // connection instance creation
  var connection: Connection = null
  try {
    Class.forName(driver)
    connection = DriverManager.getConnection(url, username, password)
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery("select title,genre from movies.movies; ")
    while (resultSet.next()) {
      val title = resultSet.getString("title")
      val genere = resultSet.getString("genre")
      println(s"ttle of move is ${title} and gener of the movie is ${genere}")
    }
  } catch {
    case e: Exception => e.printStackTrace()
  }
  finally {
    connection.close()
  }
}
