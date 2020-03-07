package io.herain.moneytou.app.graphql.type

import java.util.*

data class Transfer(
    val id: UUID,
    val expensePart: Expense,
    val incomePart: Income
)
