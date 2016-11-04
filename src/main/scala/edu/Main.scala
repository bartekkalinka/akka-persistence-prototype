package edu

object Main extends App {
  if(args(0) == "migration") {
    val migration = new MigrationMain
    migration.migrate()
  }
  else {
    val http = new AkkaHttpMain(args(0))
  }
}