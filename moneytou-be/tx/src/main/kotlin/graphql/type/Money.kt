package io.herain.moneytou.tx.graphql.type

import io.herain.moneytou.common.domain.Currency
import java.math.BigDecimal

data class Money(
        val value: BigDecimal,
        val currency: Currency
)
