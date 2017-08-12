import org.scalatest.{FlatSpec, FunSuite}

/**
  * Created by vladyslav.bachurin on 8/12/2017.
  */
class SeatsArrangementTest extends FlatSpec {

  // A B C   D E F G   H J K

  "Family of 3" should "get 3 places in a middle section " in {
    assert(FlightSeats.availableSeatsFor3("1A 1B 1C 1G 1H 1J 1K", 1) == List((1, DEF)))
  }

  it should "get 3 places in a side section " in {
    assert(FlightSeats.availableSeatsFor3("1D 1E 1F 1G 1H 1J 1K", 1) == List((1, ABC)))
  }

  it should "get places together in an alomost empty plane" in {
    assert(FlightSeats.numAvailableSeatsFor3("1A 2B 3J", 3) == 9)
  }

  it should "not get 3 places if others in the staggered order" in {
    assert(FlightSeats.numAvailableSeatsFor3(
      "1A 1C 1E 1G 1J " +
      "2B 2D 2F 2H 2K " +
      "3A 3C 3E 3G 3J ", 3) == 0)
  }

  it should "ignore seat numbers in the incorrect format" in {
    assert(FlightSeats.numAvailableSeatsFor3("1C 1H 1 E", 1) == 2)
  }

  it should "get all 4 options of batches of 3 in the empty row" in {
    assert(FlightSeats.numAvailableSeatsFor3("", 1) == 4)
  }

}
