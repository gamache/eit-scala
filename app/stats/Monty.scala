package stats
import models._

trait Monty {
  val gamesByPlayer: Map[Player, List[Game]]
  val ascensionsByPlayer: Map[Player, List[Game]]

  /*  This one's kind of a bitch.  The awards:

      Birdie:     ascending each gender
      Double Top: ascending each gender and alignment
      Hat Trick:  ascending each gender, alignment, and race
      Grand Slam: ascending each gender, alignment, race, and role
      Full Monty: ascending each gender, alignment, race, role, and conduct

      And the modifier "with bells on" signifies that the award was earned
      with a streak of games in which every game was necessary to achieve
      the award; that is, removal of any game would result in no award.
  */


  // Only look at players who've ascended at least the minimum number of
  // times, for performance.

  private val ascensionCountByPlayer: Map[Player, Int] =
    ascensionsByPlayer.mapValues(_.size)

  private val maybeBirdie: Map[Player, List[Game]] =
    ascensionCountByPlayer
      .filter(_._2 >= 2)
      .map(kv => (kv._1, gamesByPlayer(kv._1)))

  private val maybeDoubleTop: Map[Player, List[Game]] =
    ascensionCountByPlayer
      .filter(_._2 >= 3)
      .map(kv => (kv._1, gamesByPlayer(kv._1)))

  private val maybeHatTrick: Map[Player, List[Game]] =
    ascensionCountByPlayer
      .filter(_._2 >= 5)
      .map(kv => (kv._1, gamesByPlayer(kv._1)))

  private val maybeGrandSlam: Map[Player, List[Game]] =
    ascensionCountByPlayer
      .filter(_._2 >= 13)
      .map(kv => (kv._1, gamesByPlayer(kv._1)))

  private val maybeFullMonty = maybeGrandSlam


  // Perhaps the easiest way to verify Bells is to keep track of game
  // indices, and verify Bells, separately from verifying each award.
  // Game indices are 0-based and seperate for each player.
  // Only do this for players we care about.

  private val indexByGame: Map[Game, Int] =
    maybeBirdie.values.flatten.zipWithIndex.toMap

  private def isBells(games: List[Game]): Boolean = {
    val startIndex = indexByGame(games(0))
    games
      .zipWithIndex // => (game, i)
      .map(gi => (gi._1, (indexByGame(gi._1) - gi._2) == startIndex)) // => (game, bells?)
      .filter(gb => !gb._2) // remove bells games
      .size == 0
  }

}

