import org.scalatest._
import wordspec._
import matchers._

class MainSpec extends AnyWordSpec with should.Matchers {
  "a Main" should {
    "be compiled with scala 2" in {
      Main.msg shouldBe "I was compiled by Scala 2. :)"
    }
  }
}
