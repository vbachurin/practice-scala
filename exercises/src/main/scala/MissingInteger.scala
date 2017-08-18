object MissingInteger {

  def solution(a: Array[Int]): Int = {
    if (a.isEmpty) return 1
    val max = a.max
    if (max <= 0) return 1

    val range = (1 to max + 1) toArray
    val diff = range diff a // all elements contained in 'range', but not in 'a'
    diff.min
  }

}
