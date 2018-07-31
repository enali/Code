package example.extralib.akka.http

import scala.util.{Failure, Success}

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.stream.ActorMaterializer

object ClientWithSingleRequest {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    // 发送单个请求
    val responseFuture = Http().singleRequest(HttpRequest(uri="example.http://akka.io"))
    responseFuture.onComplete {
      case Success(res) => println(res)
      case Failure(_) => sys.error("something wrong")
    }
  }
}
