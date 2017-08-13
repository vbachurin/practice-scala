import org.scalatest.FunSuite

/**
  * Created by vladyslav.bachurin on 8/13/2017.
  */
class SimilarNumbersTest extends FunSuite {

  test("there are no similar numbers starting with 0") {
    assert(SimilarNumbers.similarNumberCount(100) == 1)
  }

  test("numbers are in the range between 0 and 99999") {
    assertThrows[IllegalArgumentException] {
      (SimilarNumbers.similarNumberCount(-111) == 0)
    }
    assertThrows[IllegalArgumentException] {
      assert(SimilarNumbers.similarNumberCount(100000) == 0)
    }
  }

  test("there is the only one similar number for those consisting of the only digit") {
    assert(SimilarNumbers.similarNumberCount(777) == 1)
  }

  test("two similar numbers for 12") {
    assert(SimilarNumbers.similarNumberCount(12) == 2)
  }

  test("six similar numbers for 123") {
    assert(SimilarNumbers.similarNumberCount(123) == 6)
  }

  test("similar numbers for those containing 0") {
    assert(SimilarNumbers.similarNumberCount(7890) == 18)
  }
}
