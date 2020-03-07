package io.herain.moneytou.app.graphql.type

import java.time.OffsetDateTime
import java.util.*

interface Tx {
    val id: UUID
    val amount: Money
    val date: OffsetDateTime
    val category: TxCategory
    val labels: List<Label>
    val account: Account
    val note: String?
}
