package io.herain.moneytou.tx.domain

import io.herain.moneytou.shared.domain.IdentifiedEntity
import io.herain.moneytou.tx.graphql.type.Tx
import java.time.OffsetDateTime
import java.util.UUID
import javax.persistence.CollectionTable
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.Table

@Entity
@Table(name = "transaction", schema = "moneytou")
data class Tx(
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    override val id: UUID = UUID.randomUUID(),
    @Embedded
    val amount: Money,
    @Column(name = "date", nullable = false)
    val date: OffsetDateTime,
    @Column(name = "category_id", nullable = false)
    val categoryId: UUID,
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "transaction_label",
        schema = "moneytou",
        joinColumns = [JoinColumn(name = "tx_id")]
    )
    val labels: Set<Label>,
    @Column(name = "account_id", nullable = false)
    val accountId: UUID,
    @Column(name = "note", nullable = true)
    val note: String?
) : IdentifiedEntity {
    fun asDto(): Tx {
        return Tx(
            this.id,
            this.amount.asDto(),
            this.date,
            this.categoryId,
            this.labels.map { it.asDto() }.toSet(),
            this.accountId,
            this.note
        )
    }
}
