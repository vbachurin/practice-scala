package ua.com.prjctr

import org.scalatest.FunSuite
import org.scalatest.Matchers._

class AlphabetTest extends FunSuite {

  test("testDecide") {
    Alphabet.decide("hackerhappy", "hackerrank", 9) shouldBe true
  }

  test("aba") {
    Alphabet.decide("aba", "aba", 7) shouldBe true
  }

  test("replace and add enough") {
    Alphabet.decide("hi", "hello", 5) shouldBe true
  }

  test("replace and add not enough") {
    Alphabet.decide("hi", "hello", 4) shouldBe false
  }

  test("shorten enough") {
    Alphabet.decide("heloween", "hi", 8) shouldBe true
  }

  test("shorten too many steps") {
    Alphabet.decide("happyheloween", "happyhi", 9) shouldBe false
  }

  test("shorten more than enough 2") {
    Alphabet.decide("hollywood", "hollywoo", 2) shouldBe false
  }

  test("shorten not enough") {
    Alphabet.decide("heloween", "hi", 7) shouldBe false
  }

  test("just add enough") {
    Alphabet.decide("hell", "helloween", 5) shouldBe true
  }

  test("just add not enough") {
    Alphabet.decide("hell", "helloween", 4) shouldBe false
  }

  test("more1") {
    Alphabet.decide("hackerhappy", "hackerrank", 9) shouldBe true
  }
  test("more2") {
    Alphabet.decide("hackerhapp", "hackerrank", 8) shouldBe true
  }
  test("more3") {
    Alphabet.decide("hackerhappy", "hackerrank", 100) shouldBe true
  }
  test("more4") {
    Alphabet.decide("aba", "aba", 7) shouldBe true
  }
  test("more5") {
    Alphabet.decide("aba", "aba", 8) shouldBe true
  }
  test("more6") {
    Alphabet.decide("a", "a", 1) shouldBe false
  }
  test("more7") {
    Alphabet.decide("a", "b", 2) shouldBe true
  }
  test("more8") {
    Alphabet.decide("a", "b", 3) shouldBe true
  }
  test("more9") {
    Alphabet.decide("zz", "zz", 3) shouldBe false
  }
  test("more10") {
    Alphabet.decide("aa", "aa", 4) shouldBe true
  }
  test("more11") {
    Alphabet.decide("bbb", "ccc", 7) shouldBe true
  }
  test("more12") {
    Alphabet.decide("bbb", "ccc", 6) shouldBe true
  }
  test("more13") {
    Alphabet.decide("hellow", "wolleh", 6) shouldBe false
  }
  test("more14") {
    Alphabet.decide("hellow", "wolleh", 7) shouldBe false
  }
  test("more15") {
    Alphabet.decide("hellowh", "hwolleh", 12) shouldBe true
  }
  test("more16") {
    Alphabet.decide("hellowh", "hwolleh", 14) shouldBe true
  }
  test("more17") {
    Alphabet.decide("hellowh", "hwolleh", 15) shouldBe true
  }
  test("more18") {
    Alphabet.decide("hellowh", "hwolleh", 16) shouldBe true
  }
  test("more19") {
    Alphabet.decide("hellowh", "hwolleh", 17) shouldBe true
  }
  test("more20") {
    Alphabet.decide("hellowh", "hwolleh", 11) shouldBe false
  }
  test("more21") {
    Alphabet.decide("heabcd", "hefghi", 8) shouldBe true
  }
  test("more22") {
    Alphabet.decide("heabcd", "hefghi", 9) shouldBe false
  }
  test("more23") {
    Alphabet.decide("heabcd", "hefghi", 11) shouldBe false
  }
  test("more24") {
    Alphabet.decide("abc", "abc", 2) shouldBe true
  }
  test("more25") {
    Alphabet.decide("abc", "abcd", 2) shouldBe false
  }

  test("cannot cheat with adding and removing the last char") {
    Alphabet.decide("happyheloween", "happyhi", 10) shouldBe false
  }
  test("more26") {
    Alphabet.decide("heloween", "heloweed", 4) shouldBe false
  }
  test("more27") {
    Alphabet.decide("f", "fff", 2) shouldBe true
  }
  test("more28") {
    Alphabet.decide("fff", "f", 2) shouldBe true
  }
  test("more29") {
    Alphabet.decide("f", "fff", 4) shouldBe true
  }
  test("more30") {
    Alphabet.decide("f", "fff", 3) shouldBe false
  }
  test("more31") {
    Alphabet.decide("fff", "f", 3) shouldBe false
  }
  test("more32") {
    Alphabet.decide("abba", "ababa", 5) shouldBe true
    // formula is:
    // (s.length - firstDiffIndex) + (t.length - firstDiffIndex) == k
    // firstDiffIndex should be = 2
    // (4 - 2) + (5 - 2) = 2 + 3 == 5
  }
  test("more33") {
    Alphabet.decide("abba", "ababa", 6) shouldBe false
  }
  test("more34") {
    Alphabet.decide("cck", "cc", 3) shouldBe true
  }
  val a100 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
  val b100 = a100.map(_ => 'b')
  test("long1") {
    Alphabet.decide(a100, "a", 99) shouldBe true
  }
  test("long2") {
    Alphabet.decide(a100.take(50), b100.take(50), 100) shouldBe true
  }
  test("long3") {
    Alphabet.decide(a100.take(50) + b100.take(50), b100, 100) shouldBe false
  }
  test("long4") {
    Alphabet.decide(a100.take(50), a100, 100) shouldBe true
  }
  test("long5") {
    Alphabet.decide(a100.take(50), a100, 50) shouldBe true
  }
  test("long6") {
    Alphabet.decide(a100.take(50), a100, 49) shouldBe false
  }
  test("long7") {
    Alphabet.decide(b100.take(25) + a100.take(25), a100.take(50), 100) shouldBe true
  }

}
