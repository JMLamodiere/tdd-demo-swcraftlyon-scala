package net.jmlamo.tdd_demo_swcraftlyon.application.command

import org.scalatest._
import wordspec._
import matchers._
import org.mockito.MockitoSugar

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class RegisterRunningSessionHandlerSpec
    extends AnyWordSpec
    with should.Matchers
    with MockitoSugar {

  "a RegisterRunningSessionHandler" should {

    "create a running session containing provided informations" in {
      //Given (Arrange)

      //When (Act)
      val handler = createHandlerAndDependencies
      val resultF = handler.handle(
        RegisterRunningSession(
          125.7,
          "shoes"
        )
      )

      //Then (Assert)
      val result = Await.result(resultF, 5.second)
      result.distance shouldBe 125.7
      result.shoes shouldBe "shoes"
    }
  }

  private def createHandlerAndDependencies: RegisterRunningSessionHandler = {
    new RegisterRunningSessionHandler
  }
}
