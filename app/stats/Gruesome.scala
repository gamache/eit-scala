package stats

trait Gruesome {
  def games: List[models.Game]

  def gruesomeDeaths(): List[(models.Player, Int)] = {
    games
      .filter(_.death == "eaten by a Grue")
      .groupBy(_.player)
      .mapValues(_.size)
      .toList
      .sortBy(- _._2)
  }
}
