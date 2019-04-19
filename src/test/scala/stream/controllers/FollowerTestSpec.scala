package stream.controllers

import org.scalatest._
import stream.controllers.Follower
import fake.FakeDatabase

class FollowerTestSpec extends FlatSpec {

    "All" should "return all the follower" in {
        assert(Follower.all === Set("PtitDoudoux"))
    }

    "follow" should "let a user follow if not blacklisted" in {
        Follower.follow("Flo")
        assert(Follower.isFollowing("Flo") === true)
    }

    "isFollowing" should "check if a user is actually following" in {
        assert(Follower.isFollowing("PtitDoudoux") === true)
    }
}
