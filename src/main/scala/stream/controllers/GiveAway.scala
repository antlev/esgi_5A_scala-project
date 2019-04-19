package stream.controllers

import scala.util.Random
import fake.FakeDatabase
import stream.controllers.{Tips, User}


/**
  * GiveAway controller to handling operations about them
  */
object GiveAway {

    /**
      * Create a Giveaway
      * @param name the giveaway name
      */
    def createGiveAway (name: String) : Unit = FakeDatabase.createGiveAway(name)

    /**
      * Subscribe a user to a giveaway only of he is not blacklisted
      * @param giveawayName The giveaway name to subscribe on
      * @param username The username
      * @return If the user is participating or not
      */
    def particpate (giveawayName: String, username: String) : Boolean = {
        var hasParticipate = false
        if (!User.isBlacklisted(username))
            hasParticipate = FakeDatabase.participateToGiveAway(giveawayName, username)
        hasParticipate
    }

    /**
      * Loot a gain for a user for a given giveaway
      * @param giveawayName The giveaway to loot from
      * @return The username of the winner
      */
    def loot (giveawayName: String) : String = {
        FakeDatabase.findGivewayByName(giveawayName) match {
            case Some(ga) =>
                val amountByUser = ga.users.map(u => (u, Tips.amountsSumByUser(u)))
                val totalAmount = amountByUser.reduce((u1, u2) => u1._2 + u2._2)
                var winByUser: List[(String , Int, Int)] = List()
                val randWin = new Random().nextInt(totalAmount.toInt)
                var winAcc = 0.0
                for (user <- amountByUser) {
                    val userWin = (user._1, winAcc, winAcc + user._2)
                    winAcc += user._2
                    winByUser :+ userWin
                }
                // Pattern matching necessary ? It cannot be not found
                winByUser.find(wbu => wbu._2 < randWin && wbu._3 > randWin).get._1
            case None => "NO GIVEAWAY"
        }
    }
}
