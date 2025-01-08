import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import org.example.Price
import org.example.Price.Companion.MINIMUM_VALUE
import org.junit.jupiter.api.assertThrows

class PriceTest : AnnotationSpec() {
    @Test
    fun `금액은 음수라면 런타임 에러가 발생한다`() {
        val newValue = -1

        val error = assertThrows<IllegalArgumentException> { Price.of(newValue) }

        error.message shouldBe "$MINIMUM_VALUE 이상 값이어야 합니다."
    }
}