package models

class Scoreboard(val name:  String, val games: List[Game])
extends stats.HighScore {

  val gamesByPlayer: Map[Player, List[Game]] = games.groupBy(_.player)

  def gbp(): Map[Player, List[Game]] = gamesByPlayer
}


