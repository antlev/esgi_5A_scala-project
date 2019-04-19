package stream.controllers

import fake.FakeDatabase

object Follower {

    def all : Set[String] = FakeDatabase.followers

    def isFollowing (follower: String) : Boolean = FakeDatabase.followers.contains(follower)

}
