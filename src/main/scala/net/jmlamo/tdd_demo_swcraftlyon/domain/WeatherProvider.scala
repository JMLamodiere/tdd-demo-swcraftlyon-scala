package net.jmlamo.tdd_demo_swcraftlyon.domain

import scala.concurrent.{ExecutionContext, Future}

trait WeatherProvider {
  def getCurrentCelsiusTemperature()(implicit
      ec: ExecutionContext
  ): Future[Double]

}
