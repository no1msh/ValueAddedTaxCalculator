package com.no1msh.vat.calculator

import com.no1msh.vat.mapper.toHalfUpPrice
import com.no1msh.vat.price.Price
import com.no1msh.vat.price.ProductPrice
import java.math.BigDecimal
import java.math.RoundingMode

object VatCalculator {
    fun calculateByTotal(
        total: Price,
    ): ProductPrice {
        val vatRate: BigDecimal =
            BigDecimal(1).divide(BigDecimal(11), 10, RoundingMode.HALF_UP)

        val vat: BigDecimal = BigDecimal(total.value).multiply(vatRate)

        val halfUpVat: Price = vat.toHalfUpPrice()

        return ProductPrice(
            purchasePrice = Price.ZERO,
            valueOfSupply = total - halfUpVat,
            vat = halfUpVat,
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
