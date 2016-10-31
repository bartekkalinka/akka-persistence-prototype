package edu

import spray.json._

trait JsonProtocol extends DefaultJsonProtocol {
  implicit val command1Format = jsonFormat2(Command1.apply)
}
