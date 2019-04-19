package stream

import akka.http.scaladsl.server.Directives
import stream.controllers.Tips
import stream.json.{Donators, JsonSupport, TipsSum, TipsSumUserList}

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
        path("tipsSum") {
            get {
                complete(TipsSum(Tips.amountsSum()))
            }
        }~
        path("tipsSumAllUser") {
            get {
                complete(TipsSumUserList(Tips.amountsSumAllUser()))
            }
        }
        //pathPrefix("tipsSumByUser" / String ) {
        //    user => {
        //        get {
        //            complete(TipsSum(Tips.amountsSumByUser(user)))
        //        }
        //    }
        //}
    }
}