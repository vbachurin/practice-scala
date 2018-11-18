import org.scalatest._

class FirstNonRepeatingCharacterTest extends FlatSpec with Matchers {

//  "firstNotRepeatingCharacter" should "work if last element is grid duplicate" in {
//    FirstNonRepeatingCharacter.firstNotRepeatingCharacter("bcb") should be ('c')
//  }
  "firstNotRepeatingCharacter" should "work" in {
    FirstNonRepeatingCharacter.firstNotRepeatingCharacter("abacabad") should be ('c')
  }

}
