package fake

import akka.Done

object FakeDatabase {

    final case class Item(id: Long, name: String, amount: Double)
    final case class GiveAway(id: Long, name: String, var users: List[String])

    private var USERIDCOUNT = 3
    private var GIVEAWAYIDCOUNT = 0
    private var ORDERS : List[Item] = List(Item(1, "Flo", 100.0), Item(2, "PtitDoudoux", 50.0), Item(3, "Antoine", 20.0))
    private var BLACKLISTED: Set[String] = Set("Antoine")
    private var FOLLOWERS: Set[String] = Set("PtitDoudoux")
    private var GIVEAWAY: List[GiveAway] = List()

    /**
      * Get all the `Item`
      * @return A list of `    Item`
      */
    def allItems : List[Item] = ORDERS

    /**
      * Get an `Item` by his ID
      * @param itemId The ID to search for
      * @return An option containing an Item
      */
    def fetchItemById (itemId: Long) : Option[Item] = ORDERS.find(el => el.id == itemId)

    /**
      * Get multiple `Item` by user
      * @param user The username
      * @return The list of `Item`
      */
    def fetchItemsByUser (user: String) : List[Item] = ORDERS.filter(el => el.name == user)

    /**
      * Save an `Item in the DB`
      * @param name The username
      * @param amount The amount of the tips
      */
    def saveItem (name: String, amount: Double) : Unit = {
        USERIDCOUNT += 1
        ORDERS :+ Item(USERIDCOUNT, name, amount)
    }

    /**
      * Save a list of `Item`
      * @param order The list of `Item` to insert in the fakeDB
      */
    def saveOrder (order: List[(String, Double)]) : Unit = for (el <- order) saveItem(el._1, el._2)

    /**
      * Get all the blacklisted user
      * @return All the blacklisted user of the stream
      */
    def blacklisted  : Set[String] = BLACKLISTED

    /**
      * Blacklist a user
      * @param user The username
      */
    def blacklist (user: String) : Unit = BLACKLISTED += user

    /**
      * Remove a User from the blacklist
      * @param user The username
      */
    def removeFromBlacklist (user: String) : Unit = BLACKLISTED -= user

    /**
      * Get all the followers for the streamer
      * @return All the followers of the streamer
      */
    def followers  : Set[String] = FOLLOWERS

    /**
      * Make a user follow the streamer
      * @param user The username
      */
    def follow (user: String) : Unit = FOLLOWERS += user

    /**
      * Make a user unfollow the streamer
      * @param user The username
      */
    def unfollow (user: String) : Unit = FOLLOWERS -= user

    /**
      * Find a giveaway by a name
      * @param name The giveaway name to find
      * @return An Option for the GivaAway
      */
    def findGivewayByName (name: String) : Option[GiveAway] = GIVEAWAY.find(el => el.name == name)

    /**
      * Create a giveaway in the fakeDB
      * @param name The giveaway name
      */
    def createGiveAway (name: String) : Unit = {
        GIVEAWAYIDCOUNT += 1
        GIVEAWAY :+ GiveAway(GIVEAWAYIDCOUNT, name, List())
    }

    /**
      * Allow a user to participate to a giveaway
      * @param giveAwayName The name of the giveaway
      * @param username The name of the user to subscribe
      * @return The success of the subscription to the giveaway for a user
      */
    def participateToGiveAway(giveAwayName: String, username: String) : Boolean = {
        findGivewayByName(giveAwayName) match {
            case Some(giveaway) =>
                giveaway.users :+ username
                true
            case None => false
        }
    }
}
