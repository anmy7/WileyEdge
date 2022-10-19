package classActivities

import java.sql.{Connection, DriverManager, PreparedStatement}
object MySQLInsert extends App{
  val driver="com.mysql.jdbc.Driver"
  val url="jdbc:mysql://localhost:3306/movies"
  val username="root"
  val password="root"
  // connection instance creation
  var connection:Connection = null
  try{
    Class.forName(driver)
    connection=DriverManager.getConnection(url,username,password)
    val insertMySQL="""INSERT INTO movies (title,genre,director,release_year) values(?,?,?,?)"""
    val preparedStatement:PreparedStatement=connection.prepareStatement(insertMySQL)
    preparedStatement.setString(1,"ABC")
    preparedStatement.setString(2,"comedy")
    preparedStatement.setString(3,"temp")
    preparedStatement.setString(4,"2022")
    preparedStatement.execute()
  }catch {
    case e:Exception => e.printStackTrace()
  }
  finally {
    connection.close()
  }
}