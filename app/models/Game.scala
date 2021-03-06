// there is so much boilerplate, it breaks my little dynamically-typed heart

package models

import java.util.Date

class Game(
  var player:      Player,

  var gameId:      String,
  var version:     String,
  var name:        String,
  var death:       String,
  var role:        String,
  var race:        String,
  var gender:      String,
  var gender0:     String,
  var align:       String,
  var align0:      String,

  var deathdate:   Date,
  var birthdate:   Date,

  var points:      Long,

  var deathdnum:   Int,
  var deathlev:    Int,
  var maxlvl:      Int,
  var hp:          Int,
  var maxhp:       Int,
  var deaths:      Int,
  var uid:         Int,
  var turns:       Int,
  var realtime:    Int,
  var starttime:   Int,
  var endtime:     Int,
  var extinctions: Int,
  var kills120:    Int,

  var conduct:     Int, // bitfield
  var achieve:     Int  // bitfield
) {

  def nConducts(): Int = countSetBits(conduct)
  def foodless():         Boolean = (conduct & 1<<0) != 0
  def vegan():            Boolean = (conduct & 1<<1) != 0
  def vegetarian():       Boolean = (conduct & 1<<2) != 0
  def atheist():          Boolean = (conduct & 1<<3) != 0
  def weaponless():       Boolean = (conduct & 1<<4) != 0
  def pacifist():         Boolean = (conduct & 1<<5) != 0
  def illiterate():       Boolean = (conduct & 1<<6) != 0
  def polypileless():     Boolean = (conduct & 1<<7) != 0
  def polyselfless():     Boolean = (conduct & 1<<8) != 0
  def wishless():         Boolean = (conduct & 1<<9) != 0
  def artifactWishless(): Boolean = (conduct & 1<<10) != 0
  def genocideless():     Boolean = (conduct & 1<<11) != 0

  def nAchievements(): Int = countSetBits(achieve)
  def gotBell():          Boolean = (achieve & 1<<0) != 0
  def enteredGehennom():  Boolean = (achieve & 1<<1) != 0
  def gotCandelabrum():   Boolean = (achieve & 1<<2) != 0
  def gotBook():          Boolean = (achieve & 1<<3) != 0
  def didInvocation():    Boolean = (achieve & 1<<4) != 0
  def gotAmulet():        Boolean = (achieve & 1<<5) != 0
  def reachedElemental(): Boolean = (achieve & 1<<6) != 0
  def reachedAstral():    Boolean = (achieve & 1<<7) != 0
  def ascended():         Boolean = (achieve & 1<<8) != 0
  def didMines():         Boolean = (achieve & 1<<9) != 0
  def didSokoban():       Boolean = (achieve & 1<<10) != 0
  def killedMedusa():     Boolean = (achieve & 1<<11) != 0

  private def countSetBits(bitfield: Int): Int = {
    0.until(16).filter(i => (bitfield & 1 << i) != 0).size
  }
}


object Game {

  def fromXlogLine(line: String): Game = {
    // An Xlogfile line looks like this (no line breaks):
    //
    // 1-0 version=3.4.3:points=0:deathdnum=0:deathlev=1:maxlvl=1:hp=14:
    //  maxhp=14:deaths=0:deathdate=20121030:birthdate=20121030:uid=904:
    //  role=Mon:race=Hum:gender=Fem:align=Law:name=wizard:death=quit:
    //  conduct=0xfff:turns=1:achieve=0x0:realtime=43:starttime=1351614451:
    //  endtime=1351619500:gender0=Fem:align0=Law:extinctions=0:kills120=0
    //
    // "1-0" is the gameId, and following a space, the rest is a colon-
    // separated key=value string.

    val (gameId, paramStream) = splitFirst(line, " ")
    val params = Map(
      paramStream.split(":").map(p => splitFirst(p, "=")):_ *
    )

    val stringKeys = List("version", "name", "death", "role", "race",
                          "gender", "gender0", "align", "align0")
    val List(version, name, death, role, race, gender, gender0, align, align0) =
      stringKeys.map(k => params(k))

    val dateKeys = List("deathdate", "birthdate")
    val List(deathdate, birthdate) = dateKeys.map(k => strToDate(params(k)))

    val points = params("points").toLong

    val intKeys = List("deathdnum", "deathlev", "maxlvl", "hp",
                       "maxhp", "deaths", "uid", "turns", "realtime",
                       "starttime", "endtime", "extinctions", "kills120")
    val List(deathdnum, deathlev, maxlvl, hp, maxhp, deaths, uid,
      turns, realtime, starttime, endtime, extinctions, kills120) =
      intKeys.map(k => params(k).toInt)

    val bitfieldKeys = List("conduct", "achieve")
    val List(conduct, achieve) = bitfieldKeys.map(k => strToBitfieldInt(params(k)))


    val game = new Game(
      Player(name),
      gameId,
      version, name, death, role, race, gender, gender0, align, align0,
      deathdate, birthdate,
      points,
      deathdnum, deathlev, maxlvl, hp, maxhp, deaths, uid,
        turns, realtime, starttime, endtime, extinctions, kills120,
      conduct, achieve
    )

    game
  }

  // parses "20120415" into a Date representing April 15, 2013
  private def strToDate(str: String): java.util.Date = {
    val fmt = new java.text.SimpleDateFormat("yyyyMMdd")
    return fmt.parse(str)
  }

  // parses "0x010" into an Int 16
  private def strToBitfieldInt(str: String): Int = {
    return java.lang.Long.decode(str).toInt
  }

  // splits str in two at the first occurrence of c.  If c is not present,
  // returns List(str, "').
  private def splitFirst(str: String, c: String): (String, String) = {
    val idx = str.indexOf(c)
    if (idx < 0) return (str, "")
    (str.substring(0, idx), str.substring(idx+1))
  }

}
