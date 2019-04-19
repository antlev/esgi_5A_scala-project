package stream.controllers

import fake.FakeDatabase
import stream.json.TipsSumUser
import stream.controllers.User


/**
  * Tips controller to handling operations about them
  */
object Tips {

    /**
      * Donate to streamer, also check if the user is not blacklisted
      * @param user The username
      * @param amount The amount to give
      * @return If the operation passed or not
      */
    def donate (user: String, amount: Double) : Boolean =
        if (!User.isBlacklisted(user)) {
            FakeDatabase.saveItem(user, amount)
            true
        } else false

    /**
      * Get the list of all donators
      * @return The list of donators
      */
    def donators () : List[String] = FakeDatabase.allItems.map(_.name)


    /**
      * Get the amount of tips given to the stream
      * @return The total amount of tips
      */
    def amountsSum () : Double = {
        FakeDatabase.allItems.map(_.amount).sum
        // FakeDatabase.allItems.reduce(_.amount + _.amount)  | Don't work WTF
    }

    /**
      * Get the amount of tips given to the stream by a user
      * @return The total amount of tips from a given user
      */
    def amountsSumByUser (user: String) : Double = {
        FakeDatabase.fetchItemsByUser(user).map(_.amount).sum
        // FakeDatabase.fetchItemsByUser(user).reduce(_.amount + _.amount)  | Don't work WTF
    }

    /**
      * Get the amount of tips given to the stream by each user
      * @return The total amount of tips by each user
      */
    def amountsSumAllUser () : List[TipsSumUser] = {
        for (user <- donators()) yield TipsSumUser(user, amountsSumByUser(user))
    }

}
