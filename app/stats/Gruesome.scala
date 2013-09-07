package stats

trait Gruesome {
  def games: List[models.Game]

  /** For each player, number of games in which they were eaten by a Grue. */
  def gruesomeDeaths(): List[(models.Player, Int)] = {
    games
      .filter(_.death == "eaten by a Grue")
      .groupBy(_.player)
      .mapValues(_.size)
      .toList
      .sortBy(- _._2)
  }
}
