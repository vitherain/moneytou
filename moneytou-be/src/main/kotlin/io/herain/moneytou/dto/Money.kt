package io.herain.moneytou.dto

import io.herain.moneytou.domain.Currency
import java.math.BigDecimal

data class Money(
        val value: BigDecimal,
        val currency: Currency
)
