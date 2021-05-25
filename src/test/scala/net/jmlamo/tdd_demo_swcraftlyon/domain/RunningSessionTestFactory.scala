package net.jmlamo.tdd_demo_swcraftlyon.domain

object RunningSessionTestFactory {
  def create(
      id: Int = 99,
      distance: Double = 999.9,
      shoes: String = "shoes not used",
      celsiusTemperature: Double = 99.9
  ): RunningSession =
    RunningSession(id, distance, shoes, celsiusTemperature)
}
