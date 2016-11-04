package edu

import akka.actor.ActorSystem
import akka.persistence.cassandra.query.scaladsl.CassandraReadJournal
import akka.persistence.journal.writer.JournalWriter
import akka.persistence.query.PersistenceQuery
import akka.persistence.query.scaladsl.{CurrentEventsByPersistenceIdQuery, CurrentPersistenceIdsQuery, ReadJournal}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink
import com.typesafe.config.ConfigFactory

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class MigrationMain {

  def migrate() = {
    val configFile = ConfigFactory.load("java-to-avro-migration")

    implicit val system = ActorSystem("MySystem", Some(configFile))
    implicit val materializer = ActorMaterializer()
    import system.dispatcher

    val readJournal =
      PersistenceQuery(system).readJournalFor[CassandraReadJournal]("cassandra-query-journal")
//      PersistenceQuery(system).readJournalFor("akka.persistence.query.journal")
//        .asInstanceOf[ReadJournal with CurrentPersistenceIdsQuery with CurrentEventsByPersistenceIdQuery]

    Await.result(
      readJournal.currentPersistenceIds().flatMapConcat { pid =>
        readJournal.currentEventsByPersistenceId(pid, 0, Long.MaxValue)
      } //.runWith(Sink.foreach(eventEnv => println(eventEnv)))
        .grouped(100).runWith(JournalWriter.sink("akka.persistence.target.journal"))
    , Duration.Inf)
  }
}

