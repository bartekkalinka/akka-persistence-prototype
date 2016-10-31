package edu

import akka.persistence.PersistentActor

object MainActor {
  case class State(aggr1: String, aggr2: Long) {
    def update(event: Event) = event match {
      case e: Event1 => copy(aggr1 = aggr1 + e.field1)
      case e: Event2 => copy(aggr2 = aggr2 + e.field2)
    }
  }
}

class MainActor extends PersistentActor {
  import MainActor._

  var state: State = State("", 0L)

  private def persistEvent(e: Event) = persist(e) { event =>
    state = state.update(event)
  }

  def receiveCommand: Receive = {
    case c: Command1 =>
      persistEvent(Event1(c.field1))
      persistEvent(Event2(c.field2))
  }

  def receiveRecover: Receive = {
    case e: Event =>
      state = state.update(e)
  }

  def persistenceId: String = s"main-actor-${self.path.name}"
}

