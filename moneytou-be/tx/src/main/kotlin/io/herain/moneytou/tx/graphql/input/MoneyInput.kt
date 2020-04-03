package io.herain.moneytou.tx.graphql.input

import io.herain.moneytou.tx.domain.Currency
import java.math.BigDecimal

data class MoneyInput(
    val value: BigDecimal,
    val currency: Currency
)
