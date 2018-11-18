package ua.com.prjctr

import scala.io.StdIn

object Alphabet {

  //  def decide(s: String, t: String, k: Int): Boolean = {
  //    val zipped = s.zip(t)
  //    val firstDiffIndex = zipped.indexWhere { case (ss, tt) => ss != tt }
  //    firstDiffIndex match {
  //      case -1 =>
  //        if (s.length == t.length)
  //          s.length + t.length <= k
  //        else
  //          (s.length - zipped.length) + (t.length - zipped.length) == k
  //      case i if i >= 0 => (s.length - i) + (t.length - i) == k
  ////      case _ if s.length == t.length =>
  ////        s.length + t.length <= k
  //      case _ => false
  //    }
  //  }

  def decide(s: String, t: String, k: Int): Boolean = {
    if (k - s.length >= t.length) // if enough steps to delete all 's' and build 't' from scratch
      true
    else {
      val zipped = s.zip(t) // zip two strings together into a sequence of tuples (<char>, <char>)
      // (if the lengths of string are different, the tail of longer string gets truncated)
      val firstDiffIndex = zipped.indexWhere { case (ss, tt) => ss != tt } // and find the position where strings start to differ
      if (firstDiffIndex == -1) { // if no difference was found
          val lengthDelta = Math.abs(s.length - t.length)
          lengthDelta <= k && lengthDelta % 2 == k % 2 // enough steps AND can do (remove last + add last) if needed
      } else // if the difference in strings was found
        (s.length - firstDiffIndex) + (t.length - firstDiffIndex) == k // then won't need to touch the common part, for the unique parts need operations
    }
  }

  //  def decide(s: String, t: String, k: Int): Boolean = {
  //
  //  }
  //
  //  def decideRec(sList: List[Char], tList: List[Char], k: Int): Boolean = {
  //    (sList, tList) match {
  //      case (s :: ss, t :: ts) =>
  //    }
  //  }

  def main(args: Array[String]) {

    val s = StdIn.readLine()
    val t = StdIn.readLine()
    val k = StdIn.readInt()

    val answer = decide(s, t, k) match {
      case true  => "Yes"
      case false => "No"
    }
    println(answer)
  }
}
