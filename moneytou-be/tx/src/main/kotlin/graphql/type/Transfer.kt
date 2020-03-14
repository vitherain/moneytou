package io.herain.moneytou.tx.graphql.type

import java.util.UUID

data class Transfer(
    val id: UUID,
    val expensePart: Expense,
    val incomePart: Income
)
