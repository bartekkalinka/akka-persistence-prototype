package edu

import akka.actor.{ActorRef, ActorSystem}
import akka.event.LoggingAdapter
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import scala.concurrent.ExecutionContextExecutor

trait Api extends JsonProtocol {
  implicit val system: ActorSystem
  implicit def executor: ExecutionContextExecutor

  val config: Config
  val logger: LoggingAdapter
  val mainActor: ActorRef

  val postsCommand1 = pathPrefix("command1") {
    pathEndOrSingleSlash {
      (post & entity(as[Command1])) { request =>
        complete {
          mainActor ! request
          OK -> "Ok"
        }
      }
    }
  }

  val routes = postsCommand1
}

