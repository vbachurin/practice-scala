package stackoverflow

import org.scalatest.{FunSuite, BeforeAndAfterAll}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD
import java.io.File

@RunWith(classOf[JUnitRunner])
class StackOverflowSuite extends FunSuite with BeforeAndAfterAll {

  import StackOverflow._

  lazy val testObject = new StackOverflow {
    override val langs =
      List(
        "JavaScript", "Java", "PHP", "Python", "C#", "C++", "Ruby", "CSS",
        "Objective-C", "Perl", "Scala", "Haskell", "MATLAB", "Clojure", "Groovy")
    override def langSpread = 50000
    override def kmeansKernels = 45
    override def kmeansEta: Double = 20.0D
    override def kmeansMaxIterations = 120
  }

  test("testObject can be instantiated") {
    val instantiatable = try {
      testObject
      true
    } catch {
      case _: Throwable => false
    }
    assert(instantiatable, "Can't instantiate a StackOverflow object")
  }

    val rdd10001447 = sc.parallelize(Seq(
      Posting(1,10001447,None,None,0,Some("JavaScript")),
      Posting(2,10001474,None,Some(10001447),1,None),
      Posting(2,10001477,None,Some(10001447),2,None),
      Posting(2,10001485,None,Some(10001447),1,None),
      Posting(2,10001519,None,Some(10001447),1,None)
    ))

  test("'groupedPostings' should work") {
    val tuples: Iterable[(Posting, Posting)] = Seq(
      (Posting(1, 10001447, None, None, 0, Some("JavaScript")), Posting(2, 10001474, None, Some(10001447), 1, None)),
      (Posting(1, 10001447, None, None, 0, Some("JavaScript")), Posting(2, 10001477, None, Some(10001447), 2, None)),
      (Posting(1, 10001447, None, None, 0, Some("JavaScript")), Posting(2, 10001485, None, Some(10001447), 1, None)),
      (Posting(1, 10001447, None, None, 0, Some("JavaScript")), Posting(2, 10001519, None, Some(10001447), 1, None))
    )

    val expected: RDD[(Int, Iterable[(Posting, Posting)])] = sc.parallelize(Seq(
      (10001447, tuples
      )
    ))
    val actual = groupedPostings(rdd10001447)
    assertResult(expected.collect(), "hz")(actual.collect())
  }


  test("'scoredPostings' should work") {
    val tuples: Iterable[(Posting, Posting)] = Seq(
      (Posting(1, 10001447, None, None, 0, Some("JavaScript")), Posting(2, 10001474, None, Some(10001447), 1, None)),
      (Posting(1, 10001447, None, None, 0, Some("JavaScript")), Posting(2, 10001477, None, Some(10001447), 2, None)),
      (Posting(1, 10001447, None, None, 0, Some("JavaScript")), Posting(2, 10001485, None, Some(10001447), 1, None)),
      (Posting(1, 10001447, None, None, 0, Some("JavaScript")), Posting(2, 10001519, None, Some(10001447), 1, None))
    )

    val grouped: RDD[(Int, Iterable[(Posting, Posting)])] = sc.parallelize(Seq(
      (10001447, tuples
      )
    ))

    val expected = sc.parallelize(Seq((Posting(1, 10001447, None, None, 0, Some("JavaScript")), 2)))

    val actual = scoredPostings(grouped)
    assertResult(expected.collect(), "hz")(actual.collect())
  }

  test("cluster") {

    val vectors = StackOverflow.sc.parallelize(List( (450000, 39),(500000, 31),(150000,1),(150000,10),(500000, 55),(150000,2) ,(150000,22)))

    val means = Array((500000, 13),(150000,10))

    var results: Array[(String, Double, Int, Int)] = testObject.clusterResults(means, vectors)

    testObject.printResults(results)

    println(results(0))

    println(results(1))

    assert(results.contains("Python", 100.0, 4, 6)) //I like python~!

    assert(results.contains("Scala", 66.66666666666666, 3,39))

  }

//  test("'clusterResults' should work") {
//    val vectors = sc.parallelize(Seq(
//      (350000, 67),
//      (100000, 89),
//      (300000, 3),
//      (50000, 30),
//      (350000, 77),
//      (100000, 99),
//      (300000, 13),
//      (50000, 40),
//      (350000, 87),
//      (100000, 99),
//      (300000, 23),
//      (50000, 50),
//      (200000, 20),
//      (200000, 30),
//      (200000, 40)
//    ))
////
////    val grouped: RDD[(Int, Iterable[(Posting, Posting)])] = sc.parallelize(Seq(
////      (10001447, tuples
////      )
////    ))
////
////    val scored  = scoredPostings(grouped)
////    val vectors = vectorPostings(scored)
//    val means   = kmeans(sampleVectors(vectors), vectors, debug = true)
//
//    // Array[(String, Double, Int, Int)]
//    val expected = Array(("JavaScript", 1.0, 5, 1))
//
//    val actual = clusterResults(means, vectors)
//    assertResult(expected, "hz")(actual)
//  }


}
