import streams.{InfiniteTerrain, Solver, StringParserTerrain}

/**
  * A level constructed using the `InfiniteTerrain` trait which defines
  * the terrain to be valid at every position.
  */
object InfiniteLevel extends Solver with InfiniteTerrain {
  val startPos = Pos(1,3)
  val goal = Pos(5,8)
//def neighborsWithHistory(b: Block, history: List[Move]): Stream[(Block, List[Move])] = {
  def neighborsWithHistory2(b: Block, history: List[Move]): Stream[(Block, List[Move])] = {

    (for {
        neighs <- b.legalNeighbors
      } yield (neighs._1, neighs._2 :: history)
      ).toStream

  }

  val trying = this.neighborsWithHistory2(Block(Pos(1,1),Pos(1,1)), List(Left,Up)).toSet
}

println(InfiniteLevel.trying)
//println(InfiniteLevel.solution)

/**
  * A simple level constructed using the StringParserTerrain
  */
abstract class Level extends Solver with StringParserTerrain

object Level0 extends Level {
  val level =
    """------
      |--ST--
      |--oo--
      |--oo--
      |------""".stripMargin
}

//println(Level0.solution)