import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class ExampleTest : BehaviorSpec({
    Given("2 and 3 are provided") {
        val two = 2
        val three = 3

        When("they are added") {
            val actual = two + three

            Then("the result is 5") {
                actual shouldBe 5
            }
        }
    }
})