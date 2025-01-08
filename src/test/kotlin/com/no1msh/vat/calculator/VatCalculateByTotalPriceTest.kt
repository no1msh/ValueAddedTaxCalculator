package com.no1msh.vat.calculator

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import com.no1msh.vat.price.Price
import com.no1msh.vat.price.ProductPrice
import io.kotest.assertions.assertSoftly

class VatCalculateByTotalPriceTest : BehaviorSpec({
    Given("부가세가 정수로 나누어 떨어지는 합계금액으로 5500원이 주어지고") {
        val total = Price.of(5500)
        val purchasePrice = Price.of(1100)

        When("매입액 없이, 합계금액을 기준삼아 부가세를 계산하면") {
            val actual: ProductPrice = VatCalculator.calculateByTotal(
                total = total,
                purchasePrice = Price.ZERO,
            )

            Then("부가세는 500원이다.") {
                actual.vat shouldBe Price.of(500)
            }
        }

        When("매입액 있이, 합계금액을 기준삼아 부가세를 계산하면") {
            val actual: ProductPrice = VatCalculator.calculateByTotal(
                total = total,
                purchasePrice = purchasePrice,
            )

            Then("부가세는 400원이다.") {
                actual.vat shouldBe Price.of(400)
            }
        }
    }

    Given("부가세가 정수로 나누어 떨어지지 않는 합계금액들이 주어지고") {
        // 부가세 계산시 소수점 아래 첫째자리 값이 4가 나오는 합계금액
        val total1: Price = Price.of(4900)

        // 부가세 계산시 소수점 아래 첫째자리 값이 5가 나오는 합계금액
        val total2: Price = Price.of(5000)

        When("매입액 없이, 합계금액을 기준삼아 부가세를 계산하면") {
            val actual1: ProductPrice = VatCalculator.calculateByTotal(
                total = total1,
                purchasePrice = Price.ZERO,
            )
            val actual2: ProductPrice = VatCalculator.calculateByTotal(
                total = total2,
                purchasePrice = Price.ZERO,
            )

            Then("부가세는 소수점 아래 반올림 처리된다.") {
                assertSoftly {
                    actual1.vat shouldBe Price.of(445) // 445.45454545.. -> 내림
                    actual2.vat shouldBe Price.of(455) // 454.54545454.. -> 올림
                }
            }
        }
    }
})