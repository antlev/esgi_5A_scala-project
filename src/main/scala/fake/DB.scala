package fake

import akka.Done

object FakeDatabase {

    final case class Item(id: Long, name: String, amount: Double)
    final case class Order(items: List[Item])

    private var idCount = 3
    var orders: List[Item] = List(Item(1, "Flo", 100.0), Item(2, "PtitDoudoux", 50.0), Item(3, "Antoine", 20.0))
    var blackListed: Set[String] = Set()
    var followers: Set[String] = Set()


    def fetchItem (itemId: Long) : List[Item] = orders.filter(el => el.id == itemId)

    def saveItem (name: String, amount: Double) : Unit = {
        idCount += 1
        orders ::: Item(idCount, name, amount)
    }

    def saveOrder (order: List[(String, Double)]) : Unit = for (el <- order) saveItem(el._1, el._2)

    def blacklist (user: String) : Unit = blackListed :: user

    def removeFromBlacklist (user: String) : Unit = blackListed -= user

    def isBlacklisted (user: String) : Boolean = blackListed.contains(user)

    def follow (user: String) : Unit = followers :: user

    def removeFromfollowers (user: String) : Unit = followers -= user

    def isFollowing (user: String) : Boolean = followers.contains(user)
}
