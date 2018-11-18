package ua.com.prjctr

import org.scalatest.FunSuite
import org.scalatest.Matchers._

class ArraysTest extends FunSuite {

  test("lucky middle") {
    Arrays.equilibrium(Array(1, 2, 3, 3)) shouldBe Some(true)
//    Arrays.equilibrium0(Array(1, 2, 3, 3), 2) shouldBe true
  }

  test("unlucky middle") {
    Arrays.equilibrium(Array(1, 2, 3)) shouldBe None
//    Arrays.equilibrium0(Array(1, 2, 3), 2) shouldBe false
  }

  test("lucky splitter closer to beginning") {
//    Arrays.equilibrium0(Array(2, 3, 1, 1, 1, 3), 2) shouldBe true
    Arrays.equilibrium(Array(2, 3, 1, 1, 1, 3)) shouldBe Some(true)
  }

  test("lucky splitter closer to end") {
//    Arrays.equilibrium0(Array(1, 1, 1, 1, 3), 3) shouldBe true
    Arrays.equilibrium(Array(1, 1, 1, 1, 3)) shouldBe Some(true)
  }

  test("lucky one element") {
    Arrays.equilibrium(Array(1, 1, 1)) shouldBe Some(true)
//    Arrays.equilibrium0(Array(1, 1, 1), 1) shouldBe true
  }

}
