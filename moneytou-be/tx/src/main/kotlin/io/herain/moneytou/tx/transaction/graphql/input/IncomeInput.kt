package io.herain.moneytou.tx.transaction.graphql.input

import java.time.OffsetDateTime
import java.util.UUID

data class IncomeInput(
    val id: UUID?,
    val amount: MoneyInput,
    val date: OffsetDateTime,
    val categoryId: UUID,
    val labels: List<LabelInput>,
    val accountId: UUID,
    val note: String?
)
