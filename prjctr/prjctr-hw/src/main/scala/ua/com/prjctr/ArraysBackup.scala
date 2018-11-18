package ua.com.prjctr

object ArraysBackup {

  //  def equilibrium(inputList: Array[Int]): Boolean = {
  //    (0 to inputList.length)
  //      .map { splitter =>
  //        val (l, r) = inputList.splitAt(splitter)
  //        val ll = l.sum
  //        val rr = r.drop(1).sum
  //        ll == rr
  //      }
  //      .foldLeft(false)(_ || _)
  //  }

  //  def equilibrium(inputList: Array[Int], splitter: Int) = {
  //
  //    equilibrium0(inputList, 0, 0)
  //  }
  //  def equilibrium0(inputList: Array[Int],
  //                  pLeftSum: Int,
  //                  pRightSum: Int): Boolean = {
  //    if (inputList.size == 1)
  //    val (l, r) = inputList.splitAt(inputList.size / 2)
  //    val lSum = l.sum + pLeftSum
  //    val rSum = r.drop(1).sum + pRightSum
  //    lSum - rSum match {
  //      case ll if ll > 0 =>
  //        equilibrium0(inputList.take(inputList.size / 2), pLeftSum, pRightSum + inputList.drop(inputList.size / 2).sum)
  //      case rr if rr < 0 =>
  //        equilibrium0(inputList.drop(inputList.size / 2), pLeftSum + inputList.take(inputList.size / 2).sum, pRightSum)
  //      case 0 => true
  //    }
  //  }

  def equilibrium(inputList: Array[Int]): Boolean = {
    (0 to inputList.length)
      .map { splitter =>
        //        val (l, r) = inputList.splitAt(splitter)
        val l = inputList.take(splitter)
        val r = inputList.takeRight(splitter)
        val ll = l.sum
        val rr = r.drop(1).sum
        ll == rr
      }
      .foldLeft(false)(_ || _)
  }
  //  def equilibrium(inputList: Array[Int], splitter: Int): Boolean = {
  //    if (splitter == 0) false
  //    else {
  //      val (l, r) = inputList.splitAt(splitter)
  //      val lSum = l.sum
  //      val rSum = r.drop(1).sum
  //      lSum - rSum match {
  //        case ll if ll > 0 => equilibrium(inputList, (splitter / 2) - 1) // left part is greater, set splitter to the middle of left part
  //        case rr if rr < 0 => equilibrium(inputList, splitter + (splitter / 2) - 1) // right part is greater, set splitter to the middle of right part
  //        case 0 => true
  //      }
  //    }
  //  }

}
