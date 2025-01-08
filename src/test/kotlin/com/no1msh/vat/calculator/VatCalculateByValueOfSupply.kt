package com.no1msh.vat.calculator

import com.no1msh.vat.price.Price
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class VatCalculateByValueOfSupply : BehaviorSpec({
    Given("부가세가 정수로 나누어떨어지게 되는 공급가액으로 5000원이 주어지고") {
        val valueOfSupply = Price.of(5000)
        val purchasePrice = Price.of(500)

        When("매입액 없이, 공급가액을 기준삼아 부가세를 계산하면") {
            val actual = VatCalculator.calculateByValueOfSupply(
                valueOfSupply = valueOfSupply,
                purchasePrice = Price.ZERO,
            )

            Then("부가세는 500원이다.") {
                actual.vat shouldBe Price.of(500)
            }
        }

        When("매입액 있이, 공급가액을 기준삼아 부가세를 계산하면") {
            val actual = VatCalculator.calculateByValueOfSupply(
                valueOfSupply = valueOfSupply,
                purchasePrice = purchasePrice,
            )

            Then("부가세는 400원이다.") {
                actual.vat shouldBe Price.of(450)
            }
        }
    }
})