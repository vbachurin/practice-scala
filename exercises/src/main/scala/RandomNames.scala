/**
  * Created by vladyslav.bachurin on 7/12/2017.
  */
object RandomNames extends App {

  val pairs = Map("vlad" -> 0.35, "anton" -> 0.15, "dima" -> 0.5)

  def getName(rand: Double, pairs: Map[String, Double]): String = {
    // work with List in order to use pattern matching
    def getName(rand: Double, pairs: List[(String, Double)]): String = {
      pairs match {
        case (name, _) :: Nil => name // last element
        case (name, freq) :: tail =>
          if (rand - freq < 0) name // return the result
          else getName(rand - freq, tail) // current name is `not frequent enough' for current rand, continue with tail
      }
    }
    getName(rand, pairs.to[List])
  }


  val rand = new scala.util.Random(1)
  for (i <- 1 to 10) {
    println(s"${getName(rand.nextDouble(), pairs)}")
  }

}
