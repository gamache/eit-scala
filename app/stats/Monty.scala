package stats
import models._

trait Monty {
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

  private val maybeBirdie: Map[Player, List[Game]] =
    ascensionsByPlayer.filter(_._2.size >= 2)

  private val maybeDoubleTop: Map[Player, List[Game]] =
    maybeBirdie.filter(_._2.size >= 3)

  private val maybeHatTrick: Map[Player, List[Game]] =
    maybeDoubleTop.filter(_._2.size >= 5)

  private val maybeGrandSlam: Map[Player, List[Game]] =
    maybeHatTrick.filter(_._2.size >= 13)

  private val maybeFullMonty: Map[Player, List[Game]] = maybeGrandSlam


}

