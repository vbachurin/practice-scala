import RemoveKFromList.ListNode
import org.scalatest.{FunSuite, Matchers}

class RemoveKFromListTest extends FunSuite with Matchers {

  test("testRemoveKFromListRemoveAll") {
    val first = new ListNode[Int](3)
    val l = Some(first)

    RemoveKFromList.removeKFromList(l, 3) should be (None)
  }

  test("testRemoveKFromListOneValid") {
    val first = new ListNode[Int](1)
    val l = Some(first)

    RemoveKFromList.removeKFromList(l, 3) should be (l)
  }

  test("testRemoveKFromListLeaveOne") {
    val first = new ListNode[Int](1)
    val l = Some(first)
    val second = Some(new ListNode[Int](3))
    first.next = second

    RemoveKFromList.removeKFromList(l, 3) should be (l)
  }

  test("testRemoveKFromList") {
    // l = [3, 1, 2, 3, 4, 5]
    val first = new ListNode[Int](1)
    val l = first
    val second = Some(new ListNode[Int](3))
    first.next = second
    val third = Some(new ListNode[Int](2))
    second.map(_.next = third)
    val fourth = Some(new ListNode[Int](1))
    third.map(_.next = fourth)

    println(l)
    RemoveKFromList.removeKFromList(Some(l), 3) should be (second)
  }

//  test("testRemoveKFromList2") {
//    val l = List(3, 1, 2, 3, 4, 5)
//    RemoveKFromList2.removeKFromList2(l, 3) should be (List(1, 2, 4, 5))
//  }

}
