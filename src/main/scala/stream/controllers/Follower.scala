package stream.controllers

import fake.FakeDatabase


/**
  * Follower controller to handling operations about them
  */
object Follower {

    /**
      * Get all the follower
      * @return All the follower of the Streamer
      */
    def all : Set[String] = FakeDatabase.followers

    /**
      * Check if a user is following the streamer
      * @param follower the username of the follower
      * @return If a user follow the stream or not
      */
    def isFollowing (follower: String) : Boolean = FakeDatabase.followers.contains(follower)

    /**
      * Let a user follow the stream
      * @param follower the username of the follower
      */
    def follow (follower: String) : Unit = FakeDatabase.follow(follower)

}
