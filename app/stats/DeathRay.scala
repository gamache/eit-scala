package stats

trait DeathRay {
  val games: List[models.Game]

  /** For each player, number of games in which they died by death ray. */
  def deathRay(): List[(models.Player, Int)] = {
    games
      .filter(g => g.death.indexOf("death ray") > 0)
      .groupBy(_.player)
      .mapValues(_.size)
      .toList
      .sortBy(- _._2)
  }
}

