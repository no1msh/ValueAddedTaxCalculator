import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import org.example.Price
import org.example.Price.Companion.MAXIMUM_VALUE
import org.example.Price.Companion.MINIMUM_VALUE
import org.junit.jupiter.api.assertThrows

class PriceTest : AnnotationSpec() {
    @Test
    fun `금액은 음수라면 런타임 에러가 발생한다`() {
        val newValue = -1

        val error = assertThrows<IllegalArgumentException> { Price.of(newValue) }

        error.message shouldBe "$MINIMUM_VALUE 이상 $MAXIMUM_VALUE 이하의 값이어야 합니다."
    }

    @Test
    fun `금액이 10억을 초과한다면 런타임 에러가 발생한다`() {
        val newValue = 1_000_000_001

        val error = assertThrows<IllegalArgumentException> { Price.of(newValue) }

        error.message shouldBe "$MINIMUM_VALUE 이상 $MAXIMUM_VALUE 이하의 값이어야 합니다."
    }

    @Test
    fun `금액이 0이상 10억이하면 금액 객체를 생성할 수 있다`() {
        assertSoftly {
            shouldNotThrowAny { Price.of(0) }
            shouldNotThrowAny { Price.of(1_000_000_000) }
        }
    }
}
