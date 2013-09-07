package stats

trait BestBehaved {
  val games: List[models.Game]

  /** Games with the most conducts followed. */
  def bestBehaved(): List[(models.Player, models.Game)] = {
    games
      .sortBy(- _.nConducts)
      .map(g => (g.player, g))
  }
}

