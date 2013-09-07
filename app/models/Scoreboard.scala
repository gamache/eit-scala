package models
import stats._

class Scoreboard(val name:  String, val games: List[Game])
extends HighScore
   with Anorexics
   with Ascensions
   with BestBehaved
   with BestOf13
   with DeathRay
   with FirstDeath
   with Gruesome
{
  val gamesByPlayer: Map[Player, List[Game]] = games.groupBy(_.player)
}

object Scoreboard {
  def test(): Scoreboard = {
    val gc = new GameCollection("2013")
    gc.loadXlog("scores.xlogfile.2012")
    gc.scoreboard
  }
}


