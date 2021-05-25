package net.jmlamo.tdd_demo_swcraftlyon.infrastructure.akkahttp

import akka.http.scaladsl.model.MediaTypes._
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.stephenn.scalatest.jsonassert.JsonMatchers
import net.jmlamo.tdd_demo_swcraftlyon.application.command.{
  RegisterRunningSession,
  RegisterRunningSessionHandler
}
import org.mockito.MockitoSugar
import org.scalatest.matchers._
import org.scalatest.wordspec._

class RunningSessionPostControllerSpec
    extends AnyWordSpec
    with should.Matchers
    with MockitoSugar
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
  }

  private def createControllerAndDependencies
      : (RunningSessionPostController, RegisterRunningSessionHandler) = {
    val handler = mock[RegisterRunningSessionHandler]

    (new RunningSessionPostController(handler), handler)
  }
}
