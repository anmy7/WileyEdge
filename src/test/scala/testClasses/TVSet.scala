import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
class TVSet{
  private var on:Boolean= false
  def ison:Boolean=on
  def pressPowerButton(): Unit ={
    on = ! on
  }
}
class FeatureSpeTest extends AnyFeatureSpec with GivenWhenThen{
  info("As a tv set owner")
  feature("TV power button"){
    scenario("User press power button when tv is off"){
      Given(" a tv set is switched off")
      val tv= new TVSet
      assert(!tv.ison)
      When("then power button is pressed")
      tv.pressPowerButton()
      Then("really check tv is on or not")
      assert(tv.ison)
    }
  }
}