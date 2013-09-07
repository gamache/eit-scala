package stats

trait Gluttons {

  val gamesByPlayer: Map[models.Player, List[models.Game]]

  /** For each player, number of games in which they choked to death. */
  def gluttons(): List[(models.Player, Int)] = {
    gamesByPlayer
      .mapValues(gs => gs.filter(_.death.indexOf("choked") == 0).size)
      .toList
      .sortBy(- _._2)
  }
}

