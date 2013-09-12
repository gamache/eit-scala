package stats

trait Helpless {
  val gamesByPlayer: Map[models.Player, List[models.Game]]

  /** For each player, the number of deaths suffered while helpless. */
  def helpless(): List[(models.Player, Int)] = {
    gamesByPlayer
      .mapValues(gs => gs.filter(_.death.indexOf("while helpless") > 0).size)
      .toList
      .sortBy(- _._2)
  }
}

