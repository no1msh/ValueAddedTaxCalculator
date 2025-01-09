package com.no1msh.vat.calculator

import com.no1msh.vat.price.Price
import com.no1msh.vat.price.ProductPrice
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class VatCalculateByValueOfSupplyTest : BehaviorSpec({
    Given("부가세가 정수로 나누어떨어지게 되는 공급가액으로 5000원이 주어지고") {
        val valueOfSupply = Price.of(5000)
        val purchasePrice = Price.of(500)

        When("매입액 없이, 공급가액을 기준삼아 부가세를 계산하면") {
            val actual: ProductPrice = VatCalculator.calculateByValueOfSupply(
                totalSales = valueOfSupply,
                purchasePrice = Price.ZERO,
            )

            Then("부가세는 500원이다.") {
                actual shouldBe ProductPrice(
                    valueOfSupply = Price.of(5000),
                    vat = Price.of(500),
                    total = Price.of(5500)
                )
            }
        }

        When("매입액 있이, 공급가액을 기준삼아 부가세를 계산하면") {
            val actual: ProductPrice = VatCalculator.calculateByValueOfSupply(
                totalSales = valueOfSupply,
                purchasePrice = purchasePrice,
            )

            Then("부가세는 400원이다.") {
                actual shouldBe ProductPrice(
                    purchasePrice = Price.of(500),
                    valueOfSupply = Price.of(5000),
                    vat = Price.of(450),
                    total = Price.of(5450)
                )
            }
        }
    }

    Given("부가세가 정수로 나누어 떨어지지 않는 공급가액들이 주어지고") {
        // 부가세 계산시 소수점 아래 첫째자리 값이 4가 나오는 공급가액
        val valueOfSupply1: Price = Price.of(1004)

        // 부가세 계산시 소수점 아래 첫째자리 값이 5가 나오는 공급가액
        val valueOfSupply2: Price = Price.of(1005)

        When("매입액 없이, 공급가액을 기준삼아 부가세를 계산하면") {
            val actual1: ProductPrice = VatCalculator.calculateByValueOfSupply(
                totalSales = valueOfSupply1,
            )
            val actual2: ProductPrice = VatCalculator.calculateByValueOfSupply(
                totalSales = valueOfSupply2,
            )

            Then("부가세는 소수점 아래 반올림 처리된다.") {
                assertSoftly {
                    actual1 shouldBe ProductPrice(
                        valueOfSupply = valueOfSupply1,
                        vat = Price.of(100), // 100.4 -> 내림
                        total = Price.of(1104)
                    )
                    actual2 shouldBe ProductPrice(
                        valueOfSupply = valueOfSupply2,
                        vat = Price.of(101), // 100.5 -> 올림
                        total = Price.of(1106)
                    )
                }
            }
        }
    }
})
