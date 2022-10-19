package classActivities

import scalikejdbc._
object Trycode extends App{
  Class.forName("com.mysql.jdbc.Driver")
  val url="jdbc:mysql://localhost:3306/movies"
  ConnectionPool.singleton(url, "root", "root")

  // ad-hoc session provider on the REPL
  implicit val session = AutoSession

  // table creation, you can run DDL by using #execute as same as JDBC
  sql"""
create table members (
  id serial not null primary key,
  name varchar(64),
  created_at timestamp not null
)
""".execute.apply()

  // insert initial data
  Seq("Alice", "Bob", "Chris") foreach { name =>
    sql"insert into members (name, created_at) values (${name}, current_timestamp)".update.apply()
  }




}