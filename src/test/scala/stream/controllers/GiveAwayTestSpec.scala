package stream.controllers

import org.scalatest._
import stream.controllers.GiveAway
import fake.FakeDatabase

class GiveAwayTestSpec extends FlatSpec {

    "createGiveAway" should "be able to create a giveaway" in {
        assert(GiveAway.createGiveAway("foobar") === true)
        assert(GiveAway.createGiveAway("foobar") === false)  // can't create same giveaway
    }

    "participate" should "let a user participate to a giveaway" in {
        GiveAway.createGiveAway("foobar")
        GiveAway.particpate("foobar", "PtitDoudoux")
        assert(FakeDatabase.findGivewayByName("foobar").get.users.contains("PtitDoudoux") === true)
    }

    "loot" should "find a winner in giveaway" in {
        GiveAway.createGiveAway("foobar")
        GiveAway.particpate("foobar", "PtitDoudoux")
        GiveAway.particpate("foobar", "Flo")
        assert(GiveAway.loot("foobar") !== "NO GIVEAWAY")
    }

}
