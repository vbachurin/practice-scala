import org.scalatest.{FunSuite, Matchers}

class RotateImageTest extends FunSuite with Matchers {

  test("testRotateImage") {
    val a = Array(
      Array(1, 2, 3),
      Array(4, 5, 6),
      Array(7, 8, 9)
    )
    RotateImage.rotateImage(a) should be(
      Array(
        Array(7, 4, 1),
        Array(8, 5, 2),
        Array(9, 6, 3)
      )
    )
  }

}
