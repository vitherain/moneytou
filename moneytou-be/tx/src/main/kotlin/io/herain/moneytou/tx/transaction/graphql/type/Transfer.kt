package io.herain.moneytou.tx.transaction.graphql.type

import java.util.UUID

data class Transfer(
    val expensePartId: UUID,
    val incomePartId: UUID
)
