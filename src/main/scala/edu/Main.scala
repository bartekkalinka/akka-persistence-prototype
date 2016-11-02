package edu

import akka.actor.{ActorSystem, Props}
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

object Main extends App with Api {
  override implicit val system = ActorSystem()
  override implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()

  override val config = Config(ConfigFactory.load())
  override val logger = Logging(system, getClass)
  override val mainActor = system.actorOf(Props(new MainActor))

  Http().bindAndHandle(routes, config.httpInterface, config.httpPort)
}