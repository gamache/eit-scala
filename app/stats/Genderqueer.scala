package stats

trait Genderqueer {
  val gamesByPlayer: Map[models.Player, List[models.Game]]

  /** For each player, number of games in which they changed gender. */
  def genderqueer(): List[(models.Player, Int)] = {
    gamesByPlayer
      .mapValues(gs => gs.filter(g => g.gender0 != g.gender).size)
      .toList
      .sortBy(- _._2)
  }
}

