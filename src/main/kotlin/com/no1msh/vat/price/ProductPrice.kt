package com.no1msh.vat.price

/**
 * @property purchasePrice 매입가
 * @property valueOfSupply 공급가액
 * @property vat 부가가치세
 * @property total 합계금액
 * */

data class ProductPrice(
    val purchasePrice: Price,
    val valueOfSupply: Price,
    val vat: Price,
    val total: Price,
)
