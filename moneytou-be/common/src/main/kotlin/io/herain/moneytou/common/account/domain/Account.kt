package io.herain.moneytou.common.account.domain

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "account", schema = "moneytou")
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID,
    @Embedded
    val name: AccountName,
    @Column(name = "user_id", nullable = false)
    val userId: UUID
) {
    @Embeddable
    data class AccountName(
        @Column(name = "name", nullable = false)
        val name: String
    ) {
        init {
            if (name.length > 40) {
                throw IllegalArgumentException("name='${name}' must be 40 characters long at most")
            }
        }
    }
}
