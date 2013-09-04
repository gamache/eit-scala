package stats

trait BestBehaved {
  val games: List[models.Game]

  def bestBehaved(): List[(models.Player, Int)] = {
    games
      .sortBy(- _.nConducts)
      .map(g => (g.player, g.nConducts))
  }
}

