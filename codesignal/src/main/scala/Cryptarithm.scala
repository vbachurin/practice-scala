object Cryptarithm {

  def isCryptSolution(crypt: Array[String],
                      solution: Array[Array[Char]]): Boolean = {
    val solutionMap = solution.map(e => (e(0) -> e(1))).toMap

    crypt
      .map(
        word =>
          word
            .map(char => solutionMap(char)))
      .filterNot(code => code.size > 1 && code.startsWith("0"))
      .map(_.toLong)
      .toList match {
      case List(s1, s2, s3) => s1 + s2 == s3
      case _ => false
    }

  }

}
