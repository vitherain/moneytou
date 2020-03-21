package io.herain.moneytou.tx.transaction.graphql.type

import io.herain.moneytou.tx.graphql.type.Account
import io.herain.moneytou.tx.graphql.type.TxCategory
import io.herain.moneytou.tx.transaction.domain.PositiveMoney
import java.time.OffsetDateTime
import java.util.UUID

interface Tx {
    val id: UUID
    val amount: PositiveMoney
    val date: OffsetDateTime
    val category: TxCategory
    val labels: List<Label>
    val account: Account
    val note: String?
}
