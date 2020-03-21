package io.herain.moneytou.tx.transaction.domain

import io.herain.moneytou.common.domain.Currency
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
data class NegativeMoney(
    @Column(name = "amount", nullable = false)
    val value: BigDecimal,
    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    val currency: Currency
) {
    init {
        if (value.signum() != -1) {
            throw IllegalArgumentException("amount=${value} must be a negative number")
        }
    }
}
