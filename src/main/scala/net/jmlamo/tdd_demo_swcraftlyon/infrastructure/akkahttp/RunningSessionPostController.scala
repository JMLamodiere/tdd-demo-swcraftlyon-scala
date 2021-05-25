package net.jmlamo.tdd_demo_swcraftlyon.infrastructure.akkahttp

import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import net.jmlamo.tdd_demo_swcraftlyon.application.command.RegisterRunningSessionHandler

class RunningSessionPostController(handler: RegisterRunningSessionHandler) {

  lazy val route: Route =
    path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, handler.handle))
      }
    }
}
