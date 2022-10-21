package testClasses

import org.scalatest.Tag
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.tagobjects.Slow
object Super extends Tag("com.training.learning.practise.codetotest.Super")
class TaggingTest extends AnyFlatSpec{"The Scala language" must "add correctly syntax" taggedAs (Slow) in {
  val sum =1+1
  assert(sum === 2)
}
  it must "subtraxct correctly" taggedAs(Slow,Super) in{
    val diff=4-1
    assert(diff === 3)}}
