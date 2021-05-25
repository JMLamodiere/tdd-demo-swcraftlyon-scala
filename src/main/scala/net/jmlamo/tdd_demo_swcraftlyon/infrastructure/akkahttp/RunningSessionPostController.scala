package net.jmlamo.tdd_demo_swcraftlyon.infrastructure.akkahttp

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import net.jmlamo.tdd_demo_swcraftlyon.application.command.{
  RegisterRunningSession,
  RegisterRunningSessionHandler
}

import scala.concurrent.ExecutionContext

class RunningSessionPostController(handler: RegisterRunningSessionHandler)(
    implicit val ec: ExecutionContext
) extends JsonSupport {

  lazy val route: Route = path("runningsessions") {
    post {
      entity(as[RegisterRunningSession]) { command =>
        complete(
          201,
          handler.handle(command)
        )
      }
    }
  }
}
