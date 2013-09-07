package stats

trait Anorexics {
  val games: List[models.Game]

  /** For each player, total number of games in which they died of
    * starvation.
    */
  def anorexics(): List[(models.Player, Int)] = {
    games
      .filter(_.death == "died of starvation")
      .groupBy(_.player)
      .mapValues(_.size)
      .toList
      .sortBy(- _._2)
  }
}

