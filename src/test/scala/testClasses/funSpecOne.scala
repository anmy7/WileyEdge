import org.scalatest.funsuite.AnyFunSuite
import com.training.classActivities.ObjectOrientation.StudentClass
class funSpecOne extends AnyFunSuite{
  var testObj = new StudentClass()
  testObj.setName("Jinesh")
  test("StudentNameTest"){
    assert(testObj.name === "Jinesh")
  }
  test("StudentIDTest"){
    assert(testObj.id === 0)
  }
  test("IncreasedIDTest"){
    testObj.IncreaseID()
    assert(testObj.id === 1)
  }

}
