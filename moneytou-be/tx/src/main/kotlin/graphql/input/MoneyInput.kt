package io.herain.moneytou.tx.graphql.input

import io.herain.moneytou.common.domain.Currency
import java.math.BigDecimal

data class MoneyInput(
    val value: BigDecimal,
    val currency: Currency
)
