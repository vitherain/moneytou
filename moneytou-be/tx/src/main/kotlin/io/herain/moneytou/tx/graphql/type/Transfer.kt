package io.herain.moneytou.tx.graphql.type

import java.util.UUID

data class Transfer(
    val expensePartId: UUID,
    val incomePartId: UUID
)
