package models
import scala.io.Source

case class Tournament(val name:  String,
                      var games: List[Game] = List()) {
  def loadXlog(filename: String) = {
    games = games ++ Source.fromFile(filename)
                           .getLines
                           .map(l => Game.fromXlogLine(l))
  }

}

object Tournament {

}
