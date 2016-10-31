package edu

case class Config(config: com.typesafe.config.Config) {
  val listsLimit = config.getInt("business.listsLimit")
  val httpInterface = config.getString("http.interface")
  val httpPort = config.getInt("http.port")
}


