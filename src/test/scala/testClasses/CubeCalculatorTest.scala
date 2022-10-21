import org.scalatest.BeforeAndAfter
import org.scalatest.funsuite.AnyFunSuite
import testClasses.CubeCalculator

class CubeCalculatorTest extends AnyFunSuite with BeforeAndAfter{

  before {
    println("Beginning of a test")
  }

  test ("CubeCalculator Test") {
    assert(CubeCalculator.cube(5) === 125)
  }

  test("Test1"){
    var list = Array()
    assert(list.isEmpty)
  }
  after{
    println("End of a test")
  }
}
