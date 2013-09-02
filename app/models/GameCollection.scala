package models
import scala.io.Source

case class GameCollection(val name:  String,
                          var games: List[Game] = List()) {
  def loadXlog(filename: String) = {
    games = games ++ scala.io.Source.fromFile(filename)
                                    .getLines
                                    .map(l => Game.fromXlogLine(l))
  }

  def scoreboard(): Scoreboard = {
    new Scoreboard(name, games)
  }

}

object GameCollection {

}
