# akka-persistence-prototype
akka persistence journal serialization experiment

Persistent actor with one command, and 2 events, persisted to cassandra, used for testing mixed serialization.

run with java serialization: `sbt "run java"`

run with avro serialization: `sbt "run avro"`

assumes cassandra is present on `localhost:9042`

example REST call to issue the command:

     curl -X POST -H 'Content-Type: application/json' http://localhost:9000/command1 -d '{ "field1" : "c", "field2" : 23 }'
