package com.workshop

// Let's do a recursive solution
// Use match on the `n`
// You can use `|` to match on multiple constants
// you can use guard `case x if ... =>`
class Fibonacci {
  def nth(n: Int): Int = n match {
    case num if num < 0 => throw new IllegalArgumentException
    case 0 | 1 => n
    case num => nth(num - 1) + nth(num - 2)
  }
}

// 0 1 1 2 3 5 8