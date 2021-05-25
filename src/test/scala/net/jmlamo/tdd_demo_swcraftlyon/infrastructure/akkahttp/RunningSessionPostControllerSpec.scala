package net.jmlamo.tdd_demo_swcraftlyon.infrastructure.akkahttp

import akka.http.scaladsl.model.MediaTypes._
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.stephenn.scalatest.jsonassert.JsonMatchers
import net.jmlamo.tdd_demo_swcraftlyon.application.command.{
  RegisterRunningSession,
  RegisterRunningSessionHandler
}
import net.jmlamo.tdd_demo_swcraftlyon.domain.RunningSessionTestFactory
import org.mockito.{ArgumentMatchersSugar, MockitoSugar}
import org.scalatest.matchers._
import org.scalatest.wordspec._

import scala.concurrent.{ExecutionContext, Future}

class RunningSessionPostControllerSpec
    extends AnyWordSpec
    with should.Matchers
    with MockitoSugar
    with ArgumentMatchersSugar
    with ScalatestRouteTest
    with JsonMatchers {

  "a RunningSessionPostController" should {

    "pass a command to the handler with data from HTTP call" in {
      //Given (Arrange)
      val (controller, handler) = createControllerAndDependencies

      //When (Act)
      Post(
        "/runningsessions",
        HttpEntity(
          `application/json`,
          """
|{
|  "distance": 5.5,
|  "shoes": "Adadis Turbo2"
|}
|""".stripMargin
        )
      ) ~> controller.route

      // Then (Assert)
      val expectedCommand = RegisterRunningSession(5.5, "Adadis Turbo2")
      verify(handler).handle(expectedCommand)
    }

    "use the handler result to send an HTTP response" in {
      //Given (Arrange)
      val (controller, handler) = createControllerAndDependencies
      when(handler.handle(RegisterRunningSession(5.5, "Adadis Turbo2")))
        .thenReturn(
          Future.successful(
            RunningSessionTestFactory.create(42, 5.5, "Adadis Turbo2", 37.2)
          )
        )

      //When (Act)
      val result = Post(
        "/runningsessions",
        HttpEntity(
          `application/json`,
          """
            |{
            |  "distance": 5.5,
            |  "shoes": "Adadis Turbo2"
            |}
            |""".stripMargin
        )
      ) ~> controller.route

      // Then (Assert)
      result ~> check {
        status.intValue() shouldBe 201
        contentType.toString shouldBe "application/json"
        responseAs[String] should matchJson("""
|{
|  "distance": 5.5,
|  "shoes": "Adadis Turbo2",
|  "id": 42,
|  "celsiusTemperature": 37.2
|}""".stripMargin)
      }
    }
  }

  private def createControllerAndDependencies
      : (RunningSessionPostController, RegisterRunningSessionHandler) = {
    val handler = mock[RegisterRunningSessionHandler]
    givenHandlerReturnsAnEntity(handler)

    (new RunningSessionPostController(handler), handler)
  }

  private def givenHandlerReturnsAnEntity(
      handler: RegisterRunningSessionHandler
  ) = {
    when(handler.handle(any[RegisterRunningSession])(any[ExecutionContext]))
      .thenReturn(Future.successful(RunningSessionTestFactory.create()))
  }
}
