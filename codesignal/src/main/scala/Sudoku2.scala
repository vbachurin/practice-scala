object Sudoku2 {

  def sudoku2(grid: Array[Array[Char]]): Boolean = {
    rows(grid).verify() && cols(grid).verify() && cubes(grid).verify()
  }

  def rows(grid: Array[Array[Char]]) = grid

  def cols(grid: Array[Array[Char]]) = rows(grid.transpose)

  def cubes(grid: Array[Array[Char]]): Array[Array[Char]] = {
//    (0 until 9 by 3)
//      .map(skip => extractFromCube(skip, grid))
//      .toArray
    (
      for {
        i <- (0 until 9 by 3)
        j <- (0 until 9 by 3)
      } yield extractFromCube(i, j, grid)
    ).toArray
  }

  def extractFromCube(skipX: Int,
                      skipY: Int,
                      grid: Array[Array[Char]]): Array[Char] = {
    grid
      .drop(skipX)
      .take(3)
      .flatMap(_.drop(skipY)
        .take(3))
  }

  implicit class RichSudoku(grid: Array[Array[Char]]) {

    def verify() = {
      grid
        .forall(
          _.groupBy(identity) // group by char
            .filter { case (char, chars) => char != '.' } // omit '.'
            .map { case (char, chars) => chars.size } // leave only count of digits
            .forall(_ <= 1)) // make sure each digit is used once
    }
  }
}
