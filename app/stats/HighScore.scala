package stats
import models._

trait HighScore {
  def gamesByPlayer: Map[Player, List[Game]]
  def games: List[Game]

  /** For each player, highest total scores for all games played. */
  def highTotalScores(): List[(Player, Long)] = {
    gamesByPlayer
      .mapValues(v => v.foldLeft(0L){_+_.points})  // sum all games' scores
      .toList
      .sortBy(- _._2)
  }

  /** For each player, highest per-game average score. */
  def highAverageScores(): List[(Player, Long)] = {
    highTotalScores
      .map(s => (s._1, s._2 / gamesByPlayer(s._1).size))
      .toList
      .sortBy(- _._2)
  }

  /** Games with highest scores. */
  def highSingleScores(): List[(Player, Game)] = {
    games
      .sortBy(- _.points)
      .map(g => (g.player, g))
  }
}
