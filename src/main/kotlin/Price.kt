package org.example

@JvmInline
value class Price private constructor(val value: Int) {

    companion object {
        internal const val MINIMUM_VALUE = 0
        internal const val MAXIMUM_VALUE = 1_000_000_000

        fun of(newValue: Int): Price {
            require(newValue in MINIMUM_VALUE..MAXIMUM_VALUE) {
                "$MINIMUM_VALUE 이상 $MAXIMUM_VALUE 이하의 값이어야 합니다."
            }
            return Price(newValue)
        }
    }
}
