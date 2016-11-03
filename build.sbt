name := "akka-persistence-prototype"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= {
   val akkaV       = "2.4.11"
   List(
      "com.typesafe.akka" %% "akka-persistence" % akkaV,
      "com.typesafe.akka" %% "akka-stream" % akkaV,
      "com.typesafe.akka" %% "akka-http-experimental" % akkaV,
      "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaV,
      "org.slf4j" % "slf4j-simple" % "1.7.21",
      "com.typesafe.akka" %% "akka-persistence-cassandra" % "0.19",
      "com.sksamuel.avro4s" %% "avro4s-core" % "1.6.2"
   )
}
