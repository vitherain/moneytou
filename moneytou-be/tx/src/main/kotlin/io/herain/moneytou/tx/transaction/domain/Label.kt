package io.herain.moneytou.tx.transaction.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Label(
    @Column(name = "name", nullable = false)
    val name: String
) {
    init {
        if (name.length > 40) {
            throw IllegalArgumentException("name='${name}' must be 40 characters long at most")
        }
    }
}
