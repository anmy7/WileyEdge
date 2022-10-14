package slideExercises

object ControlStructures5 extends App{
  def productOfUnicode(text: String): Long = {
    var result: Long = 1
    text.foreach((char:Char)=>{
      result *= text.codePointAt(text.indexOf(char))
    })
    result
  }
  productOfUnicode("Hello")
}
