package net.jmlamo.tdd_demo_swcraftlyon.infrastructure.akkahttp

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import net.jmlamo.tdd_demo_swcraftlyon.application.command.RegisterRunningSession
import net.jmlamo.tdd_demo_swcraftlyon.domain.RunningSession
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val registerRunningSessionFormat = jsonFormat2(
    RegisterRunningSession
  )

  implicit val runningSessionFormat = jsonFormat4(RunningSession)
}
