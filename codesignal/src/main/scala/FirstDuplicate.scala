

object FirstDuplicate {

   def firstDuplicate(a: Array[Int]): Int = {
       findFirstDuplicate(a, Nil)
   }

   def findFirstDuplicate(a: Array[Int], met: List[Int]): Int = {
     a.headOption match {
       case Some(x) =>
         if (met.contains(x))
           x
         else
           findFirstDuplicate(a.tail, x :: met)
       case _ => -1
     }
   }

}
