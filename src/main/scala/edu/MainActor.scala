package edu

import akka.actor.ActorLogging
import akka.persistence.PersistentActor
import scala.concurrent.duration._

object MainActor {
  case class State(aggr1: String, aggr2: Long) {
    def update(event: Event) = event match {
      case e: Event1 => copy(aggr1 = aggr1 + e.field1)
      case e: Event2 => copy(aggr2 = aggr2 + e.field2)
    }
  }
}

class MainActor extends PersistentActor with ActorLogging {
  import MainActor._
  import context.dispatcher

  var state: State = State("", 0L)

  val logScheduler = context.system.scheduler.schedule(5.seconds, 5.seconds) {
    log.info(s"$persistenceId: state: $state")
  }

  private def persistEvent(e: Event) = persist(e) { event =>
    state = state.update(event)
  }

  def receiveCommand: Receive = {
    case c: Command1 =>
      log.info(s"received command $c")
      persistEvent(Event1(c.field1))
      log.info(s"persisted event ${Event1(c.field1)}")
      persistEvent(Event2(c.field2))
      log.info(s"persisted event ${Event2(c.field2)}")
  }

  def receiveRecover: Receive = {
    case e: Event =>
      state = state.update(e)
      log.info(s"recovered event $e")
  }

  def persistenceId: String = s"main-actor-${self.path.name}"
}

