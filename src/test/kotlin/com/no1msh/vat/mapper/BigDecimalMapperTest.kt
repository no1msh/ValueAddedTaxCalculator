package com.no1msh.vat.mapper

import com.no1msh.vat.price.Price
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal
import java.math.RoundingMode

class BigDecimalMapperTest: AnnotationSpec() {
    @Test
    fun `소수값이 있는 BigDecimal을 Price 객체로 바꿀 때 반올림 한다`() {
        val approximateOne = BigDecimal(10).divide(BigDecimal(11), 10, RoundingMode.UP)
        val approximateTwo = BigDecimal(23).divide(BigDecimal(11), 10, RoundingMode.UP)

        val actualOne: Price = approximateOne.toHalfUpPrice()
        val actualTwo: Price = approximateTwo.toHalfUpPrice()

        assertSoftly {
            actualOne shouldBe Price.of(1)
            actualTwo shouldBe Price.of(2)
        }
    }
}