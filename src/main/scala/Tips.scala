import fake.FakeDatabase

object Tips {

    def donate (pseudo: String, amount: Double) : Unit = FakeDatabase.saveItem(pseudo, amount)

    def donators () : List[String] = FakeDatabase.fetchAllItems().map(_.name)

    def amountsSum () : Double = FakeDatabase.fetchAllItems().map(_.amount).sum

    def amountsSumByUser (user: String) : Double = FakeDatabase.fetchItemsByUser(user).map(_.amount).sum

}
