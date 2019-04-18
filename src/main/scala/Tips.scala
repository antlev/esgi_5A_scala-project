import fake.FakeDatabase

object Tips {


    def donate (pseudo: String, amount: Double) : Unit = FakeDatabase.saveItem(pseudo, amount)

    def donators () : Iterable[String] = _

    def amountsSum () : Int = _

}
