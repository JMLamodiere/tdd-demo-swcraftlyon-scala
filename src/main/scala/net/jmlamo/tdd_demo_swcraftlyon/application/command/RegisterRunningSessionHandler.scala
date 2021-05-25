package net.jmlamo.tdd_demo_swcraftlyon.application.command

import net.jmlamo.tdd_demo_swcraftlyon.domain.RunningSession

import scala.concurrent.{ExecutionContext, Future}

class RegisterRunningSessionHandler {
  def handle(command: RegisterRunningSession)(implicit
      ec: ExecutionContext
  ): Future[RunningSession] = {
    Future.successful(
      RunningSession(
        command.distance,
        command.shoes
      )
    )

  }

}
