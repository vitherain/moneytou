package io.herain.moneytou.tx.domain

import io.herain.moneytou.tx.graphql.type.Label
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

    fun asDto(): Label {
        return Label(this.name)
    }
}
