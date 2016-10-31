package edu

case class Config(config: com.typesafe.config.Config) {
  val httpInterface = config.getString("http.interface")
  val httpPort = config.getInt("http.port")
}


