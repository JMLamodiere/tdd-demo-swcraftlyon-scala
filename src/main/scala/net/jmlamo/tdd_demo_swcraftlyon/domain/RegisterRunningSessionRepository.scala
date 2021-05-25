package net.jmlamo.tdd_demo_swcraftlyon.domain

import scala.concurrent.{ExecutionContext, Future}

trait RegisterRunningSessionRepository {
  def nextId()(implicit ec: ExecutionContext): Future[Int]
}
