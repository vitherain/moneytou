package io.herain.moneytou.app.dto

import io.herain.moneytou.app.domain.Currency
import java.math.BigDecimal

data class Money(
        val value: BigDecimal,
        val currency: Currency
)
