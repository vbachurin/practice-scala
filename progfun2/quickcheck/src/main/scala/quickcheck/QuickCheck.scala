package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = oneOf(
    const(empty),
    for {
      a <- arbitrary[A]
      h <- oneOf(const(empty), genHeap)
    } yield insert(a, h)
  )
  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)


  property("gen1-bogus12") = forAll { h: H =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("min2-bogus2") = forAll { (a: A, b: A) =>
    val h = insert(a, empty)
    val h1 = insert(b, h)
    findMin(h1) == a.min(b)
  }

  property("del2-bogus235") = forAll { h: H =>
    val h1 = insert(Int.MinValue, h)
    deleteMin(h1) == h
  }

  property("del3-bogus4") = forAll { h: H =>
    if ((isEmpty(h)) || (findMin(h) == Int.MaxValue))
      true
    else {
      val m = findMin(h)
      val g = m + 1
      val h1 = insert(g, h)
      val h11 = delAllOccursOfMin(m, h1)
      findMin(h11) == g
    }
  }

  def delAllOccursOfMin(m: A, h: H): H = {
    if (findMin(h) == m) {
      val h1 = deleteMin(h)
      delAllOccursOfMin(m, h1)
    } else h
  }

  property("list1-bogus125") = forAll { h: H =>
    def helper(h: H): List[A] = {
      if (isEmpty(h))
        List[A]()
      else
        findMin(h) :: helper(deleteMin(h))
    }
    val l = helper(h)
    l == l.sorted
  }
}
