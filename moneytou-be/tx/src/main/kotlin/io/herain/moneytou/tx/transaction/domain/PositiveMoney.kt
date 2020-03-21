package io.herain.moneytou.tx.transaction.domain

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
data class PositiveMoney(
    @Column(name = "amount", nullable = false)
    val value: BigDecimal,
    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    val currency: Currency
) {
    init {
        if (value.signum() != 1) {
            throw IllegalArgumentException("amount=${value} must be a positive number")
        }
    }
}
