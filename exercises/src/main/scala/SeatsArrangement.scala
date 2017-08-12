import scala.collection.mutable.MutableList

/**
  * Created by vladyslav.bachurin on 8/12/2017.
  */
trait Batches {
  override def toString: String = getClass.getName
}
object ABC extends Batches
object DEF extends Batches
object EFG extends Batches
object HJK extends Batches

object FlightSeats {
  def availableSeatsFor3(sold: String, rows: Int): List[(Int, Batches)] = {
    val batches = List.fill(rows)(MutableList(Option(ABC), Option(DEF), Option(EFG), Option(HJK)))

    // parse the list of the already sold tickets
    val rowsAndLetters = sold.split(" ").filter(_.length == 2).map(a => (a.charAt(0).asDigit, a.charAt(1)))

    rowsAndLetters.foreach {
      // a row can be divided into 4 batches of 3 seats: (0) A B C | (1) D E F | (2) E F G |  (3) J H K
      // if at least one seat in the batch is sold - it makes the whole batch unavailable

      // 3-seats batch A B C gets index (0)
      case (row, 'A') => batches(row - 1)(0) = None
      case (row, 'B') => batches(row - 1)(0) = None
      case (row, 'C') => batches(row - 1)(0) = None

      // 2 3-seats batches in the middle = D E F (1) and E F G (2)
      case (row, 'D') => batches(row - 1)(1) = None
      // in case a seat E or F is sold, then a family of 3 can't be placed in the section D E F G (batches (1) and (2))
      case (row, 'E') => batches(row - 1)(1) = None; batches(row - 1)(2) = None
      case (row, 'F') => batches(row - 1)(1) = None; batches(row - 1)(2) = None
      case (row, 'G') => batches(row - 1)(2) = None

      // 3-seats batch H J K gets index (3)
      case (row, 'H') => batches(row - 1)(3) = None
      case (row, 'J') => batches(row - 1)(3) = None
      case (row, 'K') => batches(row - 1)(3) = None
    }

    for {
      (batch, row) <- batches.zipWithIndex
      vacant <- batch.flatten // remove all None's
    } yield (row + 1, vacant)
  }

  def numAvailableSeatsFor3(sold: String, rows: Int): Int = {
    availableSeatsFor3(sold, rows).length
  }

  // A B C   D E F G   H J K

}
