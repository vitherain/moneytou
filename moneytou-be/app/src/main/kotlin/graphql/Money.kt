package io.herain.moneytou.app.graphql

import java.math.BigDecimal
import java.util.*

data class Money(
        val amount: BigDecimal,
        val currency: Currency
)