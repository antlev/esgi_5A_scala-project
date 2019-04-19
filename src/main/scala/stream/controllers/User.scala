package stream.controllers

import fake.FakeDatabase

object User {

    def blacklist (user: String) : Unit = FakeDatabase.blacklist(user)

    def isBlacklisted (user: String) : Boolean = FakeDatabase.blacklisted.contains(user)

}
