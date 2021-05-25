package net.jmlamo.tdd_demo_swcraftlyon.application.command

import org.scalatest._
import wordspec._
import matchers._
import net.jmlamo.tdd_demo_swcraftlyon.domain.RegisterRunningSessionRepository
import org.mockito.MockitoSugar

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class RegisterRunningSessionHandlerSpec
    extends AnyWordSpec
    with should.Matchers
    with MockitoSugar {

  "a RegisterRunningSessionHandler" should {

    "create a running session containing provided informations" in {
      //Given (Arrange)
      val (handler, repository) = createHandlerAndDependencies
      givenNextIdExists(repository)

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
      val (handler, repository) = createHandlerAndDependencies
      when(repository.nextId).thenReturn(Future.successful(12))

      //When (Act)
      val resultF = handler.handle(
        RegisterRunningSessionTestFactory.create()
      )

      //Then (Assert)
      val result = Await.result(resultF, 5.second)
      result.id shouldBe 12
    }
  }

  private def createHandlerAndDependencies
      : (RegisterRunningSessionHandler, RegisterRunningSessionRepository) = {
    val repository = mock[RegisterRunningSessionRepository]
    (new RegisterRunningSessionHandler(repository), repository)
  }

  private def givenNextIdExists(
      repository: RegisterRunningSessionRepository
  ): Unit = {
    when(repository.nextId())
      .thenReturn(Future.successful(999))
  }
}
