package net.jmlamo.tdd_demo_swcraftlyon.application.command

import net.jmlamo.tdd_demo_swcraftlyon.domain.{
  RunningSessionRepository,
  RunningSession,
  WeatherProvider
}

import scala.concurrent.{ExecutionContext, Future}

class RegisterRunningSessionHandler(
    repository: RunningSessionRepository,
    weatherProvider: WeatherProvider
) {
  def handle(command: RegisterRunningSession)(implicit
      ec: ExecutionContext
  ): Future[RunningSession] = {

    val nextIdF = repository.nextId()
    val currentTemperatureF = weatherProvider.getCurrentCelsiusTemperature()

    for {
      id <- nextIdF
      currentTemperature <- currentTemperatureF
      runningSession = RunningSession(
        id,
        command.distance,
        command.shoes,
        currentTemperature
      )
      _ <- repository.add(runningSession)
    } yield runningSession
  }
}
