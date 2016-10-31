package edu

case class Command1(field1: String, field2: Long)

sealed trait Event
case class Event1(field1: String) extends Event
case class Event2(field2: Long) extends Event

