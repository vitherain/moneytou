package io.herain.moneytou.common.user.domain

import java.util.UUID
import javax.persistence.CollectionTable
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.Table

@Entity
@Table(name = "user", schema = "moneytou")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID,
    @Embedded
    val username: UserName,
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
        name = "user_role",
        schema = "moneytou",
        joinColumns = [JoinColumn(name = "user_id")]
    )
    @Column(name = "role")
    val roles: Set<Role>
) {
    @Embeddable
    data class UserName(
        @Column(name = "username", nullable = false)
        val username: String
    ) {
        init {
            if (username.isEmpty()) {
                throw IllegalArgumentException("username must not be empty")
            }
            if (username.matches(".*\\s.*".toRegex())) {
                throw IllegalArgumentException("username='${username}' must not contain any whitespace")
            }
            if (username.length > 100) {
                throw IllegalArgumentException("username='${username}' must be 100 characters long at most")
            }
        }
    }
}
