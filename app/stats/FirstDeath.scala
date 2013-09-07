package stats

trait FirstDeath {
  val games: List[models.Game]

  /** The first 20 games ending in death, sorted earliest-to-latest.
    * May contain fewer than 20 games.
    */
  def firstDeath(): List[(models.Player, models.Game)] = {
    games
      .filter(! _.ascended)
      .sortBy(_.starttime)
      .slice(0,20)
      .map(g => (g.player, g))
  }
}

