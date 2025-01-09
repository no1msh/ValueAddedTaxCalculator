package com.no1msh.vat.calculator

import com.no1msh.vat.price.Price
import com.no1msh.vat.price.ProductPrice
import com.no1msh.vat.mapper.toHalfUpPrice
import java.math.BigDecimal
import java.math.RoundingMode

object VatCalculator {
    fun calculateByTotal(
        total: Price,
        purchasePrice: Price = Price.ZERO
    ): ProductPrice {
        val valueOfSupply: Price = total - purchasePrice
        val vatRate: BigDecimal =
            BigDecimal(1).divide(BigDecimal(11), 10, RoundingMode.HALF_UP)

        val vat: BigDecimal = BigDecimal(valueOfSupply.value).multiply(vatRate)

        return ProductPrice(
            purchasePrice = purchasePrice,
            valueOfSupply = valueOfSupply,
            vat = vat.toHalfUpPrice(),
            total = total,
        )
    }

    fun calculateByValueOfSupply(
        totalSales: Price,
        purchasePrice: Price = Price.ZERO,
    ): ProductPrice {
        val vatRate = BigDecimal(0.1)

        val totalSalesVat: BigDecimal = BigDecimal(totalSales.value).multiply(vatRate)

        if (purchasePrice == Price.ZERO) {
            val vatPrice = totalSalesVat.toHalfUpPrice()
            return ProductPrice(
                purchasePrice = purchasePrice,
                valueOfSupply = totalSales,
                vat = vatPrice,
                total = totalSales + vatPrice,
            )
        }

        val purchaseVat: BigDecimal = BigDecimal(purchasePrice.value).multiply(vatRate)
        val vatPrice = (totalSalesVat - purchaseVat).toHalfUpPrice()

        return ProductPrice(
            purchasePrice = purchasePrice,
            valueOfSupply = totalSales,
            vat = vatPrice,
            total = totalSales + vatPrice,
        )
    }
}