package io.herain.moneytou.tx.graphql.input

import java.time.OffsetDateTime
import java.util.UUID

data class ExpenseInput(
    val id: UUID?,
    val amount: MoneyInput,
    val date: OffsetDateTime,
    val categoryId: UUID,
    val labels: List<LabelInput>,
    val accountId: UUID,
    val note: String?
)
