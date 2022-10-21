
import org.scalatest.funsuite.AnyFunSuite

class TupleTesting extends AnyFunSuite{
  var tuple3 = ("Nicolas", 77)

  test("Type check"){
    assert(tuple3._2.isInstanceOf[Int] === true)
  }

  test("Tuple contains test"){
    assert(tuple3._1 === "Nicolas")
  }

  test("To string test"){
    assert(tuple3._2.toString === "77")
  }
}
