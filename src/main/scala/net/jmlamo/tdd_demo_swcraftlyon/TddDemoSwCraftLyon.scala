package net.jmlamo.tdd_demo_swcraftlyon

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import net.jmlamo.tdd_demo_swcraftlyon.application.command.RegisterRunningSessionHandler
import net.jmlamo.tdd_demo_swcraftlyon.domain.{
  RunningSession,
  RunningSessionRepository,
  WeatherProvider
}
import net.jmlamo.tdd_demo_swcraftlyon.infrastructure.akkahttp.RunningSessionPostController

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration._

object TddDemoSwCraftLyon extends App {
  implicit val system = ActorSystem(Behaviors.empty, "my-system")
  implicit val executionContext = system.executionContext

  val repository = new RunningSessionRepository {
    override def add(runningSession: RunningSession)(implicit
        ec: ExecutionContext
    ): Future[Unit] = Future.successful()

    override def nextId()(implicit ec: ExecutionContext): Future[Int] =
      Future.successful(33)
  }

  val weatherProvider = new WeatherProvider {
    override def getCurrentCelsiusTemperature()(implicit
        ec: ExecutionContext
    ): Future[Double] =
      Future.successful(12.3)
  }

  val handler = new RegisterRunningSessionHandler(repository, weatherProvider)
  val controller = new RunningSessionPostController(handler)

  val bindingFuture =
    Http()
      .newServerAt("localhost", 8000)
      .bind(controller.route)
      .map(_.addToCoordinatedShutdown(hardTerminationDeadline = 10.seconds))

  println(s"Server online at http://localhost:8000/")
}
