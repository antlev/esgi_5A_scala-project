package stream.json


import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import fake.FakeDatabase.Item
import spray.json.DefaultJsonProtocol


case class Donators(donators: List[String])
case class TipsSum(amount: Double)
case class TipsSumUser(user: String, amount: Double)
case class TipsSumUserList(sumUsers: List[TipsSumUser])


trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
    implicit val itemFormat = jsonFormat3(Item)
    implicit val donatorsFormat = jsonFormat1(Donators)
    implicit val tipsSumFormat = jsonFormat1(TipsSum)
    implicit val tipsSumUserFormat = jsonFormat2(TipsSumUser)
    implicit val tipsSumUserListFormat = jsonFormat1(TipsSumUserList)
}