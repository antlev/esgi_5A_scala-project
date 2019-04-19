import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import scala.concurrent.ExecutionContext
import stream.ApiRoute

object Server extends App {

    val host = "0.0.0.0"
    val port = 9000

    implicit val system: ActorSystem = ActorSystem("esgi-5a-scala-project")
    implicit val executor: ExecutionContext = system.dispatcher
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    Http().bindAndHandle(ApiRoute.route, host, port)
}

