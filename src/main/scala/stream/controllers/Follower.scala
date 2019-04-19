package stream.controllers

import fake.FakeDatabase

object Follower {

    def all : Set[String] = FakeDatabase.getFollowers

}
