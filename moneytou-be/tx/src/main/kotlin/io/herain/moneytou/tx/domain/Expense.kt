package io.herain.moneytou.tx.domain

import java.time.OffsetDateTime
import java.util.UUID
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
data class Expense(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    val id: UUID,
    @Embedded
    val amount: PositiveMoney,
    @Column(name = "date", nullable = false)
    val date: OffsetDateTime,
    @Column(name = "category_id", nullable = false)
    val categoryId: UUID,
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "tx_label",
        schema = "moneytou",
        joinColumns = [JoinColumn(name = "tx_id")]
    )
    val labels: Set<Label>,
    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    val account: Account,
    @Column(name = "note", nullable = true)
    val note: String?
)
