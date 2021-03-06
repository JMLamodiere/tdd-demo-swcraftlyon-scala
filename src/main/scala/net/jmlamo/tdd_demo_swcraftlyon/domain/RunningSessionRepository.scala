package net.jmlamo.tdd_demo_swcraftlyon.domain

import scala.concurrent.{ExecutionContext, Future}

trait RunningSessionRepository {
  def add(runningSession: RunningSession)(implicit
      ec: ExecutionContext
  ): Future[Unit]

  def nextId()(implicit ec: ExecutionContext): Future[Int]
}
