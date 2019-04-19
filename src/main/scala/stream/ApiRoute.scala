package stream

import akka.http.scaladsl.server.Directives
import stream.controllers.{Tips, User, Follower, GiveAway}
import stream.json.{Donators, JsonSupport, TipsSum, TipsSumUser,TipsSumUserList, Followers}

/**
  * Object which handle all route of the API
  */
object ApiRoute extends Directives with JsonSupport {

    val route = {
        path("hello") {
            get {
                complete("Hello World !")
            }
        }~
        path("donators") {
            get {
                complete(Donators(Tips.donators()))
            }
        }~
        path("followers") {
            get {
                complete(Followers(Follower.all))
            }
        }~
        path("tipsSum") {
            get {
                complete(TipsSum(Tips.amountsSum()))
            }
        }~
        path("tipsSumAllUser") {
            get {
                complete(TipsSumUserList(Tips.amountsSumAllUser()))
            }
        }~
        path("donate") {
            post {
                entity(as[TipsSumUser]) {
                    userTips => {
                        Tips.donate(userTips.user, userTips.amount) match {
                            case true => complete("Tips Donated !")
                            case false =>    complete("You can't donate, you're blacklisted.")
                        }
                    }
                }
            }
        }~
        pathPrefix("tipsSumByUser" / Segment ) {
            user => {
                get {
                    complete(TipsSum(Tips.amountsSumByUser(user)))
                }
            }
        }~
        pathPrefix("blacklist" / Segment ) {
            user => {
                get {
                    User.blacklist(user)
                    complete(s"$user is now blacklisted.")
                }
            }
        }~
        pathPrefix("createGiveAway" / Segment ) {
            giveaway => {
                get {
                    GiveAway.createGiveAway(giveaway) match {
                        case true => complete("GiveAway created !")
                        case false => complete("GiveAway can't be create")
                    }

                }
            }
        }~
        pathPrefix("participate" / Segment / Segment) {
            (giveawayName, username) => {
                get {
                    GiveAway.particpate(giveawayName, username) match {
                        case true => complete("You participate to the giveaway !")
                        case false => complete("You can't participate to the giveaway !")
                    }
                }
            }
        }~
        pathPrefix("loot" / Segment ) {
            giveaway => {
                get {
                    complete(GiveAway.loot(giveaway))
                }
            }
        }
    }
}