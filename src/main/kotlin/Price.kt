package org.example

@JvmInline
value class Price private constructor(val value: Int) {

    companion object {
        internal const val MINIMUM_VALUE = 0
        fun of(newValue: Int): Price {
            require(newValue >= MINIMUM_VALUE) { "$MINIMUM_VALUE 이상 값이어야 합니다." }
            return Price(newValue)
        }
    }
}
