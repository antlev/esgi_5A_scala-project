# ESGI 5A - Scala Project


Scala project for 5IABD
Here is the list of the route and their explanations


- "/donators"
  - GET only
  - JSON
  - Get the list of all donators

- "/followers"
  - GET only
  - JSON
  - Get the list of all followers

- "/tipsSum"
  - Get only
  - JSON
  - Get the total donation amount

- "/tipsSumAllUser"
  - Get only
  - JSON
  - Get the total donation amount for each user

- "/createGiveAway/$name"
  - GET only
  - String
  - Try to create a giveaway for a certain name

- "/participate/$giveawayName/$username"
  - GET only
  - String
  - Allow a user to participate a giveaway

- "/loot/$giveAwayName"
  - GET only
  - String
  - Give the username of the winner for a giveaway

- "/tipsSumByUser/$user"
  - GET only
  - JSON
  - Get the total donation amount for a given user

- "/blacklist/$user"
  - GET only
  - String
  - Blacklist a user by giving his name

- "/donate"
  - POST only
  - JSON
  - Allow a user to donate or to cancel a donation with a negative amount
