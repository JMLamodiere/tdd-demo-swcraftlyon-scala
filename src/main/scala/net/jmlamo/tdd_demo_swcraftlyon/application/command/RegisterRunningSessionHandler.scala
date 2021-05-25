package net.jmlamo.tdd_demo_swcraftlyon.application.command

import net.jmlamo.tdd_demo_swcraftlyon.domain.{
  RegisterRunningSessionRepository,
  RunningSession
}

import scala.concurrent.{ExecutionContext, Future}

class RegisterRunningSessionHandler(
    repository: RegisterRunningSessionRepository
) {
  def handle(command: RegisterRunningSession)(implicit
      ec: ExecutionContext
  ): Future[RunningSession] = {

    for {
      id <- repository.nextId()
    } yield RunningSession(
      id,
      command.distance,
      command.shoes
    )
  }
}
