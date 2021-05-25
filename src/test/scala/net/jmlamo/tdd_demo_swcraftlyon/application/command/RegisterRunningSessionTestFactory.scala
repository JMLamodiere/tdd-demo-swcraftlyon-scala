package net.jmlamo.tdd_demo_swcraftlyon.application.command

object RegisterRunningSessionTestFactory {
  def create(
      distance: Double = 999.9,
      shoes: String = "shoes not used"
  ): RegisterRunningSession =
    RegisterRunningSession(distance, shoes)
}
