package io.herain.moneytou.tx.graphql.type

import java.time.OffsetDateTime
import java.util.UUID

interface Tx {
    val id: UUID
    val amount: Money
    val date: OffsetDateTime
    val category: TxCategory
    val labels: List<Label>
    val account: Account
    val note: String?
}
