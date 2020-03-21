package io.herain.moneytou.tx.transaction.graphql.type

import io.herain.moneytou.tx.transaction.domain.Currency
import java.math.BigDecimal

data class Money(
    val value: BigDecimal,
    val currency: Currency
)
