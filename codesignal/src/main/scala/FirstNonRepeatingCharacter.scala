import scala.collection.mutable.ArrayBuffer

object FirstNonRepeatingCharacter {

  def firstNotRepeatingCharacter(s: String): Char = {


    val uniqueChars = s
      .groupBy(identity)
      .filter { case (char, string) => string.size == 1 }
      .map(_._1)
      .toList
    s
      .find(c => uniqueChars.contains(c))
      .getOrElse('_')
  }
}