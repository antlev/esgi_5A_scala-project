package stream.controllers

import org.scalatest._
import stream.controllers.Tips
import stream.json.TipsSumUser

class TipsTestSpec extends FlatSpec {

    "donators" should "give the list of all donators" in {
        assert(Tips.donators() === List("Flo", "PtitDoudoux", "Antoine"))
    }

    "Donate" should "be able to create a tips" in {
        assert(Tips.donate("PtitDoudoux", 10) === true)
    }

    "amountsSum" should "Sum all the tips" in {
        assert(Tips.amountsSum() === 170.0)
    }

    "AmountsSumByUser" should "give the total tips amount for a user" in {
        assert(Tips.amountsSumByUser("Flo") === 100.0)
    }

    "AmountSumAllUser" should "Sum the donation tips for all user by user" in {
        assert(Tips.amountsSumAllUser() === List(TipsSumUser("Flo", 100.0),
            TipsSumUser("PtitDoudoux", 50.0), TipsSumUser("Antoine", 20)))
    }

}
