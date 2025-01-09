package com.no1msh.vat.mapper

import com.no1msh.vat.price.Price
import java.math.BigDecimal
import java.math.RoundingMode

internal fun BigDecimal.toHalfUpPrice() =
    Price.of(this.setScale(0, RoundingMode.HALF_UP).toInt())
