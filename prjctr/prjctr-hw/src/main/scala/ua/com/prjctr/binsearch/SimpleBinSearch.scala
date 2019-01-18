package ua.com.prjctr.binsearch

object SimpleBinSearch {

  def main(args: Array[String]): Unit = {

  }

  def binarySearch(arr: Array[Int], el: Int): Int = {
    var l = 0
    var r = arr.length
    var mid: Int = 0
    while (r - l > 1) {
      println(s"l: $l, r: $r, mid: $mid, arr[mid]: ${arr(mid)}")
      mid = (r + l) / 2
      if (arr(mid) > el)
        r = mid
      else
        l = mid
    }
    return l
  }

}
