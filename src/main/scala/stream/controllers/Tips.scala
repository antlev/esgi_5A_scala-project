package stream.controllers

import fake.FakeDatabase
import stream.json.TipsSumUser

object Tips {

    def donate (user: String, amount: Double) : Unit =
        if (!FakeDatabase.getBlacklisted.contains(user)) FakeDatabase.saveItem(user, amount)

    def donators () : List[String] = FakeDatabase.allItems.map(_.name)

    def amountsSum () : Double = {
        FakeDatabase.allItems.map(_.amount).sum
        // FakeDatabase.allItems.reduce(_.amount + _.amount)  | Don't work WTF
    }

    def amountsSumByUser (user: String) : Double = {
        FakeDatabase.fetchItemsByUser(user).map(_.amount).sum
        // FakeDatabase.fetchItemsByUser(user).reduce(_.amount + _.amount)  | Don't work WTF
    }

    def amountsSumAllUser () : List[TipsSumUser] = {
        for (user <- donators()) yield TipsSumUser(user, amountsSumByUser(user))
    }

}
