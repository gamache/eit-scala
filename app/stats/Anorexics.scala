package stats

trait Anorexics {
  def games: List[models.Game]

  def anorexics(): List[(models.Player, Int)] = {
    games
      .filter(_.death == "died of starvation")
      .groupBy(_.player)
      .mapValues(_.size)
      .toList
      .sortBy(- _._2)
  }
}
