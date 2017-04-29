package calculator

object Polynomial {
  def computeDelta(a: Signal[Double], b: Signal[Double],
                   c: Signal[Double]): Signal[Double] = {
    // Δ = b² - 4ac
    Signal(Math.pow(b(), 2) - 4 * a() * c())
  }

  def computeSolutions(a: Signal[Double], b: Signal[Double],
                       c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = {
    Signal(
      delta() match {
        case pos if pos > 0 => Set[Double](
          (-b() + Math.sqrt(delta())) / (2 * a()),
          (-b() - Math.sqrt(delta())) / (2 * a())
        )
        case nill if nill == 0 => Set[Double](-b() / (2 * a()))
        case neg if neg < 0 => Set.empty
      }
    )
  }

}
