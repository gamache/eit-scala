package stats
import models._

trait BestOf13 {
  val gamesByPlayer: Map[Player, List[Game]]

  /** Fore each player, greatest number of ascensions in 13 consecutive
    * games.
    */
  def bestOf13: List[(Player, Int)] = {
    best13.map(v => (v._1, v._2.filter(_.ascended).size))
  }

  /** For each player, the 13-game streak containing the most ascensions.
    * May contain fewer than 13 games.
    */
  def best13: List[(Player, List[Game])] = {
    gamesByPlayer
      .mapValues(best13FromGames(_))
      .toList
      .sortBy(- _._2.filter(_.ascended).size)
  }

  private def best13FromGames(gs: List[Game]): List[Game] = {
    gs.sliding(13)
      .toList
      .sortBy(- _.filter(_.ascended).size)
      .apply(0)
  }
}

