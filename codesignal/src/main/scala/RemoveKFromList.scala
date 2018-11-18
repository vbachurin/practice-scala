object RemoveKFromList {

  // Definition for singly-linked list:
  case class ListNode[T](x: T) {
    var value: T = x
    var next: Option[ListNode[T]] = None

    override def toString: String = s"($x, $next)"
  }

  def removeKFromList(l: Option[ListNode[Int]],
                      k: Int): Option[ListNode[Int]] = {

    var prevVar = l
    def internalLoop(prev: Option[ListNode[Int]],
                     curr: Option[ListNode[Int]]): Option[ListNode[Int]] = {
      curr match {
        case opt @ Some(ln @ ListNode(value)) =>
          if (value == k) {
            prevVar = curr.flatMap(_ => ln.next)
            internalLoop(prevVar, curr.flatMap(_.next))
          }
          else
            prevVar = curr
            internalLoop(prevVar, curr.flatMap(_.next))
        case None => prev
      }
    }
    internalLoop(None, l)
//    def internalLoop(prev: Option[ListNode[Int]], curr: Option[ListNode[Int]]): Option[ListNode[Int]] = {
//
//    }

  }

}
