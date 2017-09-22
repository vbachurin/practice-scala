package com.workshop

// match on a tuple (divisibleBy3, divisibleBy5)
object FizzBuz {
  def eval(n: Int): String = n match {
    case num if num % 15 == 0 => "FizzBuzz"
    case num if num % 3 == 0 => "Fizz"
    case num if num % 5 == 0 => "Buzz"
    case _ => n.toString
  }
}
