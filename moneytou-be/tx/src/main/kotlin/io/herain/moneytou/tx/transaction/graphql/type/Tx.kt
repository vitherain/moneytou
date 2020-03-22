package io.herain.moneytou.tx.transaction.graphql.type

import java.time.OffsetDateTime
import java.util.UUID

data class Tx(
    val id: UUID,
    val amount: Money,
    val date: OffsetDateTime,
    val categoryId: UUID,
    val labels: Set<Label>,
    val accountId: UUID,
    val note: String?
)
