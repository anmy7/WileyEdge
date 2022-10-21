package classActivities

object TestImplicit extends App{
  val value = 10
  implicit val multiplier = 3

  def multyply(implicit by:Int) = value * by
  val result = multyply
  println(result)
}
