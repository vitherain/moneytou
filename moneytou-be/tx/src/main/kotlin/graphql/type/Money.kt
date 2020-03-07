package io.herain.moneytou.app.graphql.type

import io.herain.moneytou.app.domain.Currency
import java.math.BigDecimal

data class Money(
        val value: BigDecimal,
        val currency: Currency
)
