import org.scalatest.{FunSuite, Matchers}

class Sudoku2Test extends FunSuite with Matchers {

  val grid = Array(
    Array('1', '2', '3', '4', '5', '6', '7', '8', '9'),
    Array('1', '2', '3', '4', '5', '6', '7', '8', '9'),
    Array('1', '2', '3', '4', '5', '6', '7', '8', '9'),
    Array('1', '2', '3', '4', '5', '6', '7', '8', '9'),
    Array('1', '2', '3', '4', '5', '6', '7', '8', '9'),
    Array('1', '2', '3', '4', '5', '6', '7', '8', '9'),
    Array('1', '2', '3', '4', '5', '6', '7', '8', '9'),
    Array('1', '2', '3', '4', '5', '6', '7', '8', '9'),
    Array('1', '2', '3', '4', '5', '6', '7', '8', '9')
  )

//  test("testExtractFromCube") {
//    Sudoku2.extractFromCube(3, grid).foreach(println)
//  }

  test("cubes") {
    Sudoku2.cubes(grid).size should be (9)
      //.foreach(_.foreach(println))
  }

//  test("verify") {
//    import Sudoku2._
//    Array(Array('1', '2', '3', '4', '5', '6', '7', '.', '.')).verify() should be (true)
//  }



}
