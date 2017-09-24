Seq(0, 1, 2, 3, 4).zipWithIndex
  .filterNot{case (_, ind) => ind % 2 == 0}
  .map{case (el, _) => el}

"aaaabbbaaba".map(x => (x, 1)).groupBy(_._1)
    .map{case (_, group) => (_, group.length)}



