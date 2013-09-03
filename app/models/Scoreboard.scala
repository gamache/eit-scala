package models
import stats._

class Scoreboard(val name:  String, val games: List[Game])
extends HighScore
   with Anorexics
   with Ascensions
   //with BestBehaved
   with Gruesome
{
  val gamesByPlayer: Map[Player, List[Game]] = games.groupBy(_.player)
}


