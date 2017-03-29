package recfun

import scala.math.Ordering

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if ((c == 0) || (c == r))
      1
    else
      pascal(c - 1, r - 1) + pascal(c, r - 1)
  }


  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {

    def balance(chars: List[Char], level: Int, closingBracket: Boolean): Boolean = {
      if (chars.isEmpty)
      // the decision made basing on `opening - closing == 0'
      // and last one is a closing one
        level == 0 && closingBracket
      else
        chars.head match {
          case '(' => balance(chars.tail, level + 1, closingBracket = false)
          case ')' => balance(chars.tail, level - 1, closingBracket = true)
          case _ => balance(chars.tail, level, closingBracket)
        }
    }

    // initial level is 0, last bracket is a closing one
    balance(chars, level = 0, closingBracket = true)
  }


  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    money match {
      case 0 => 1
      case money if (money < 0 || (coins.isEmpty)) => 0
      case money => countChange(money - coins.head, coins) + countChange(money, coins.tail)
    }
  }
}


