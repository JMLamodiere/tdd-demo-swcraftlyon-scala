package net.jmlamo.tdd_demo_swcraftlyon.infrastructure.akkahttp

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._
import Directives._
import net.jmlamo.tdd_demo_swcraftlyon.application.RegisterRunningSessionHandler
import org.scalatest._
import org.scalatest.matchers._
import org.scalatest.wordspec._

class RunningSessionPostControllerSpec
    extends AnyWordSpec
    with should.Matchers
    with ScalatestRouteTest {
  "a RunningSessionPostController" should {
    "say yes" in {
      // tests:
      Post("/hello") ~> controller.route ~> check {
        responseAs[String] shouldEqual "yes"
      }
    }
  }

  private def controller = new RunningSessionPostController(
    new RegisterRunningSessionHandler
  )
}
