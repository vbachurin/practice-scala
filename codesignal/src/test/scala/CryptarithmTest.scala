import org.scalatest.{FunSuite, Matchers}

class CryptarithmTest extends FunSuite with Matchers {

  test("testIsCryptSolution") {
    val crypt: Array[String] = Array("SEND", "MORE", "MONEY")

    val solution: Array[Array[Char]] = Map(
      'O' -> '0',
      'M' -> '1',
      'Y' -> '2',
      'E' -> '5',
      'N' -> '6',
      'D' -> '7',
      'R' -> '8',
      'S' -> '9'
    ).map(e => Array(e._1, e._2)).toArray
    Cryptarithm.isCryptSolution(crypt, solution) should be(true)

  }

  test("testIsCryptSolution2") {
    val crypt: Array[String] = Array("TEN", "TWO", "ONE")

    val solution: Array[Array[Char]] = Map(
    'O' -> '1',
    'T' -> '0',
    'W' -> '9',
    'E' -> '5',
    'N' -> '4'
    ).map(e => Array(e._1, e._2)).toArray
    Cryptarithm.isCryptSolution(crypt, solution) should be(false)

  }

  test("testIsCryptSolution3") {
    val crypt: Array[String] = Array("A", "A", "A")

    val solution: Array[Array[Char]] = Map(
      'A' -> '0'
    ).map(e => Array(e._1, e._2)).toArray
    Cryptarithm.isCryptSolution(crypt, solution) should be(true)

  }

}
