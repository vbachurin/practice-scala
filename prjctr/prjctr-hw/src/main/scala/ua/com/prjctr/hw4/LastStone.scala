package ua.com.prjctr.hw4


import scala.io.StdIn

object LastStone {

  def main(args: Array[String]): Unit = {
    val pattern = raw"(\d+) (\d+) (\d+)".r
    val pattern(s, m, n) = StdIn.readLine()
    val t = StdIn.readLine().split(" ").map(_.toInt)

    val bobsWins = firstPlayersWins(m.toInt, n.toInt, t)
    println(bobsWins)
  }

  def firstPlayersWins(m: Int, n: Int, t: Array[Int]) = (m to n).count(k => playRound(k, t))

  def playRound(k: Int, t: Array[Int]): Boolean = {
    if (k < t.last && !t.contains(k))
      false
    else
      true
  }

}
