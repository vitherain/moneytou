package io.herain.moneytou.tx.domain

import io.herain.moneytou.tx.graphql.type.Label
import io.herain.moneytou.tx.graphql.type.TxCategory
import java.time.OffsetDateTime
import java.util.UUID
import javax.persistence.AttributeOverride
import javax.persistence.AttributeOverrides
import javax.persistence.CollectionTable
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "transaction", schema = "moneytou")
data class Income(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID,
    @Embedded
    val amount: NegativeMoney,
    @Column(name = "date", nullable = false)
    val date: OffsetDateTime,
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    val category: TxCategory,
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tx_label", joinColumns = [JoinColumn(name = "tx_id")])
    @AttributeOverrides(
        AttributeOverride(name = "name", column = Column(name = "name"))
    )
    val labels: Set<Label>,
    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    val account: Account,
    @Column(name = "note", nullable = true)
    val note: String?
)
