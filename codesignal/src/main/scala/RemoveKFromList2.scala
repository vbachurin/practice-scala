object RemoveKFromList2 {

  def removeKFromList2(l: List[Int], k: Int): List[Int] = {

    def internalLoop(l: List[Int], acc: List[Int]): List[Int] = {
      l match {
        case i :: ls => if (i == k)
          internalLoop(ls, acc)
        else
          internalLoop(ls, acc ++ List(i))
        case Nil   => acc
      }
    }

    internalLoop(l, Nil)
  }

}
