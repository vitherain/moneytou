package io.herain.moneytou.common.account.domain

import io.herain.moneytou.shared.domain.IdentifiedEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
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
    override val id: UUID,
    @Embedded
    val name: AccountName,
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: AccountStatus = AccountStatus.ACTIVE,
    @Column(name = "user_id", nullable = false)
    val userId: UUID
) : IdentifiedEntity {
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

    enum class AccountStatus {
        ACTIVE, INACTIVE
    }
}
