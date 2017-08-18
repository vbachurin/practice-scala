import org.scalatest.FunSuite

class MissingIntegerTest extends FunSuite {

  test("testSolution") {
    assert(MissingInteger.solution(Array(1, 3, 6, 4, 1, 2)) == 5)
    assert(MissingInteger.solution(Array(0, 1, 2, 3)) == 4)
    assert(MissingInteger.solution(Array(2)) == 1)
    assert(MissingInteger.solution(Array(-1, -3)) == 1)
    assert(MissingInteger.solution(Array()) == 1)
  }

}
