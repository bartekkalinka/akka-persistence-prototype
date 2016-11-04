package edu

import akka.actor.{ActorSystem, Props}
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

class AkkaHttpMain(args0: String) extends Api {
  val configFile = args0 match {
    case "java" =>
      ConfigFactory.load("java-serialization")
    case "avro" =>
      ConfigFactory.load("avro-serialization")
  }

  override val config = Config(configFile)
  override implicit val system = ActorSystem(name = "MySystem", config = Some(configFile))
  override implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()

  override val logger = Logging(system, getClass)

  override val mainActor = system.actorOf(Props(new MainActor))

  Http().bindAndHandle(routes, config.httpInterface, config.httpPort)
}