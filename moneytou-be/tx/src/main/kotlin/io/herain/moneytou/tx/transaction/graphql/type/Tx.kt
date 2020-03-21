package io.herain.moneytou.tx.transaction.graphql.type

import io.herain.moneytou.common.account.graphql.type.Account
import java.time.OffsetDateTime
import java.util.UUID

data class Tx(
    val id: UUID,
    val amount: Money,
    val date: OffsetDateTime,
    val category: UUID,
    val labels: List<Label>,
    val account: Account,
    val note: String?
)
