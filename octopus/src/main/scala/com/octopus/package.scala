package com

package object octopus {

  // converts the String argument value into the bytes integer
  def limitHelper(limitString: String): Int = {
    // regular expressions for parsing the limit argument
    val Bytes = "^([0-9]+)$".r
    val Kilobytes = "^([0-9]+)k$".r
    val Megabytes = "^([0-9]+)m$".r

    limitString match {
      case Bytes(value)     => value.toInt
      case Kilobytes(value) => value.toInt * 1024
      case Megabytes(value) if value.toInt < 2048 =>
        value.toInt * 1024 * 1024 // guard condition to stay under Int.MaxValue
      case other =>
        throw new IllegalArgumentException(
          s"Carefulness limit value '$other' is invalid")
    }
  }
}
