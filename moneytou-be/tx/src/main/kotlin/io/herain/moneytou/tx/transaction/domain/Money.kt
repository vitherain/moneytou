package io.herain.moneytou.tx.transaction.domain

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
data class Money(
    @Column(name = "amount", nullable = false)
    val value: BigDecimal,
    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    val currency: Currency
) {
    init {
        if (value.signum() == 0) {
            throw IllegalArgumentException("amount can not be zero")
        }
    }
}
