
import org.scalatest.funspec.AnyFunSpec

class AnySetSpectest extends AnyFunSpec{
  describe("A set"){
    describe("when empty")
    {
      it("should have size 0"){
        assert(Set.empty.size === 0)
      }
    }
  }
  it("should produce NoSuchElementExcpetion when head is invoked and call Scala team"){
    assertThrows[NoSuchElementException]{
      Set.empty.head
    }
  }
}