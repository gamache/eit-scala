package stats
import models._

trait HighScore {
  def gamesByPlayer: Map[Player, List[Game]]
  def games: List[Game]

  def highTotalScores(): List[(Player, Long)] = {
    gamesByPlayer
      .mapValues(v => v.foldLeft(0L){_+_.points})  // sum all games' scores
      .toList
      .sortBy(- _._2)
  }

  def highAverageScores(): List[(Player, Long)] = {
    highTotalScores
      .map(s => (s._1, s._2 / gamesByPlayer(s._1).size))
      .toList
      .sortBy(- _._2)
  }

  def highSingleScores(): List[(Player, Long)] = {
    games
      .sortBy(- _.points)
      .map(g => (g.player, g.points))
  }
}
