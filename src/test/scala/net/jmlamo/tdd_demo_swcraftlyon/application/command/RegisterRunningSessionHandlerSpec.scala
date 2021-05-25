package net.jmlamo.tdd_demo_swcraftlyon.application.command

import org.scalatest._
import wordspec._
import matchers._
import net.jmlamo.tdd_demo_swcraftlyon.domain.{
  RegisterRunningSessionRepository,
  RunningSession,
  WeatherProvider
}
import org.mockito.{ArgumentMatchersSugar, MockitoSugar}

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class RegisterRunningSessionHandlerSpec
    extends AnyWordSpec
    with should.Matchers
    with MockitoSugar
    with ArgumentMatchersSugar {

  "a RegisterRunningSessionHandler" should {

    "create a running session containing provided informations" in {
      //Given (Arrange)
      val (handler, _, _) = createHandlerAndDependencies

      //When (Act)
      val resultF = handler.handle(
        RegisterRunningSessionTestFactory.create(
          125.7,
          "shoes"
        )
      )

      //Then (Assert)
      val result = Await.result(resultF, 5.second)
      result.distance shouldBe 125.7
      result.shoes shouldBe "shoes"
    }

    "use next available ID when creating a running session" in {
      //Given (Arrange)
      val (handler, repository, _) = createHandlerAndDependencies
      givenNextIdIs(repository, 12)

      //When (Act)
      val resultF = handler.handle(
        RegisterRunningSessionTestFactory.create()
      )

      //Then (Assert)
      val result = Await.result(resultF, 5.second)
      result.id shouldBe 12
    }

    "store created running session" in {
      //Given (Arrange)
      val (handler, repository, weatherProvider) = createHandlerAndDependencies
      givenNextIdIs(repository, 12)
      givenCurrentCelsiusTemperatureIs(weatherProvider, 15.5)

      //When (Act)
      val resultF = handler.handle(
        RegisterRunningSessionTestFactory.create(
          125.7,
          "shoes"
        )
      )

      //Then (Assert)
      Await.result(resultF, 5.second)
      val expectedRunningSession = RunningSession(
        12,
        125.7,
        "shoes",
        15.5
      )
      verify(repository).add(expectedRunningSession)
    }

    "enrich running session with current temperature" in {
      //Given (Arrange)
      val (handler, _, weatherProvider) = createHandlerAndDependencies
      givenCurrentCelsiusTemperatureIs(weatherProvider, 15.5)

      //When (Act)
      val resultF = handler.handle(
        RegisterRunningSessionTestFactory.create()
      )

      //Then (Assert)
      val result = Await.result(resultF, 5.second)
      result.celsiusTemperature shouldBe 15.5
    }
  }

  private def createHandlerAndDependencies: (
      RegisterRunningSessionHandler,
      RegisterRunningSessionRepository,
      WeatherProvider
  ) = {
    val repository = mock[RegisterRunningSessionRepository]
    givenNextIdExists(repository)
    givenAddSucceeds(repository)

    val weatherProvider = mock[WeatherProvider]
    givenCurrentCelsiusTemperatureExists(weatherProvider)

    (
      new RegisterRunningSessionHandler(repository, weatherProvider),
      repository,
      weatherProvider
    )
  }

  private def givenNextIdIs(
      repository: RegisterRunningSessionRepository,
      id: Int
  ): Unit = {
    when(repository.nextId())
      .thenReturn(Future.successful(id))
  }

  private def givenNextIdExists(
      repository: RegisterRunningSessionRepository
  ): Unit =
    givenNextIdIs(repository, 999)

  private def givenAddSucceeds(repository: RegisterRunningSessionRepository) = {
    when(repository.add(any[RunningSession])(any[ExecutionContext]))
      .thenReturn(Future.successful(()))
  }

  private def givenCurrentCelsiusTemperatureIs(
      weatherProvider: WeatherProvider,
      temperature: Double
  ) = {
    when(weatherProvider.getCurrentCelsiusTemperature())
      .thenReturn(Future.successful(temperature))
  }

  private def givenCurrentCelsiusTemperatureExists(
      weatherProvider: WeatherProvider
  ): Unit =
    givenCurrentCelsiusTemperatureIs(weatherProvider, 99.9)
}
