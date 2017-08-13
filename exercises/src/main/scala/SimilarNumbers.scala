/**
  * Created by vladyslav.bachurin on 8/13/2017.
  */
object SimilarNumbers {

  private def numberToDigits(num: Int): List[Int] = num match {
    case 0 => Nil
    case n => n % 10 :: numberToDigits(n / 10)
  }

  def similarNumberCount(n: Int): Int = {
    require(0 <= n && n < 100000)
    numberToDigits(n).permutations.filterNot(_.head == 0).size
  }

}
