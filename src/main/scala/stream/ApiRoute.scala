package stream

import akka.http.scaladsl.server.Directives
import stream.controllers.{Tips, User, Follower}
import stream.json.{Donators, JsonSupport, TipsSum, TipsSumUser, TipsSumUserList, Followers}

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
                        val donated = Tips.donate(userTips.user, userTips.amount)
                        if (donated) complete("Tips Donated !")
                        else complete("You can't donate, you're blacklisted.")
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
        }
    }
}