package fake

import akka.Done

object FakeDatabase {

    final case class Item(id: Long, name: String, amount: Double)
    // final case class Order(items: List[Item])

    private var idCount = 3
    private var orders: List[Item] = List(Item(1, "Flo", 100.0), Item(2, "PtitDoudoux", 50.0), Item(3, "Antoine", 20.0))
    private var blackListed: Set[String] = Set()
    private var followers: Set[String] = Set()

    def allItems : List[Item] = orders
    def fetchItemById (itemId: Long) : Option[Item] = orders.find(el => el.id == itemId)
    def fetchItemsByUser (user: String) : List[Item] = orders.filter(el => el.name == user)
    def saveItem (name: String, amount: Double) : Unit = {
        idCount += 1
        orders :+ Item(idCount, name, amount)
    }
    def saveOrder (order: List[(String, Double)]) : Unit = for (el <- order) saveItem(el._1, el._2)

    def getBlacklisted  : Set[String] = blackListed
    def blacklist (user: String) : Unit = blackListed += user
    def removeFromBlacklist (user: String) : Unit = blackListed -= user
    def isBlacklisted (user: String) : Boolean = blackListed.contains(user)

    def getFollowers  : Set[String] = followers
    def follow (user: String) : Unit = followers += user
    def removeFromfollowers (user: String) : Unit = followers -= user
    def isFollowing (user: String) : Boolean = followers.contains(user)
}
