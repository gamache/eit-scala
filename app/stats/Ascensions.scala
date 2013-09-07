package stats

trait Ascensions {
  def games: List[models.Game]

  /** For each player, total number of ascensions. */
  def ascensions(): List[(models.Player, Int)] = {
    games
      .filter(_.ascended)
      .groupBy(_.player)
      .mapValues(_.size)
      .toList
      .sortBy(- _._2)
  }
}
