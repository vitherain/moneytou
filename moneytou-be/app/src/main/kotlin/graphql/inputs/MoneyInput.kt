package io.herain.moneytou.app.graphql.inputs

import io.herain.moneytou.app.domain.Currency
import java.math.BigDecimal

data class MoneyInput(
    val value: BigDecimal,
    val currency: Currency
)
