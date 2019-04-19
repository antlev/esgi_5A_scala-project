package stream.controllers

import fake.FakeDatabase

/**
  * User controller to handling operations about them
  */
object User {

    /**
      * Blacklist a user
      * @param user The username
      */
    def blacklist (user: String) : Unit = FakeDatabase.blacklist(user)

    /**
      * Check if a user is blacklisted
      * @param user The username
      * @return If a user is blacklisted or not
      */
    def isBlacklisted (user: String) : Boolean = FakeDatabase.blacklisted.contains(user)

}
