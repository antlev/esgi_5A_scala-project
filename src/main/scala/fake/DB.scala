package fake

import akka.Done

object FakeDatabase {

    final case class Item(id: Long, name: String, amount: Double)
    // final case class Order(items: List[Item])

    private var IDCOUNT = 3
    private var ORDERS : List[Item] = List(Item(1, "Flo", 100.0), Item(2, "PtitDoudoux", 50.0), Item(3, "Antoine", 20.0))
    private var BLACKLISTED: Set[String] = Set()
    private var FOLLOWERS: Set[String] = Set()

    def allItems : List[Item] = ORDERS
    def fetchItemById (itemId: Long) : Option[Item] = ORDERS.find(el => el.id == itemId)
    def fetchItemsByUser (user: String) : List[Item] = ORDERS.filter(el => el.name == user)
    def saveItem (name: String, amount: Double) : Unit = {
        IDCOUNT += 1
        ORDERS :+ Item(IDCOUNT, name, amount)
    }
    def saveOrder (order: List[(String, Double)]) : Unit = for (el <- order) saveItem(el._1, el._2)

    def blacklisted  : Set[String] = BLACKLISTED
    def blacklist (user: String) : Unit = BLACKLISTED += user
    def removeFromBlacklist (user: String) : Unit = BLACKLISTED -= user

    def followers  : Set[String] = FOLLOWERS
    def follow (user: String) : Unit = FOLLOWERS += user
    def unfollow (user: String) : Unit = FOLLOWERS -= user
}
