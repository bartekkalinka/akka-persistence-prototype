# akka-persistence-prototype
akka persistence journal serialization experiment

Persistent actor with one command, and 2 events, persisted to cassandra, used for testing mixed serialization.

java serialization version on branch `java`

avro serialization version on branch `avro`

run with `sbt run`
  
assumes cassandra is present on `localhost:9042`
