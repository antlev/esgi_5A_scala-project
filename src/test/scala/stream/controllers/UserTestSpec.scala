package stream.controllers

import org.scalatest._
import stream.controllers.User
import fake.FakeDatabase

class UserTestSpec extends FlatSpec {

    "isBlacklisted" should "check if a user is blacklisted" in {
        assert(User.isBlacklisted("Antoine") === true)
    }

    "blacklist" should "be able to be blacklist a user" in {
        User.blacklist("Flo")
        assert(User.isBlacklisted("Flo") === true)
    }
}
