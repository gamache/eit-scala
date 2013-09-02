package stats
import scala.collection.SortedMap

case class HighScoreRecord(val player: models.Player, val score: Long) {
  def compare(that: HighScoreRecord) = that.score compare this.score
}
object HighScoreRecord {
  def apply(tuple: (models.Player, Long)) = {
    new HighScoreRecord(tuple._1, tuple._2)
  }
}

trait HighScore {
  def gamesByPlayer: Map[models.Player, List[models.Game]]

  def highTotalScores(): List[HighScoreRecord] = {
    gamesByPlayer
      .mapValues(v => v.foldLeft(0L){_+_.points})  // sum all games' scores
      .map(ps => HighScoreRecord(ps))
      .toList
      .sortBy(- _.score)
  }
}
