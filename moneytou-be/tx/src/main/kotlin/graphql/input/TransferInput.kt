package io.herain.moneytou.tx.graphql.input

import java.time.OffsetDateTime
import java.util.UUID

data class TransferInput(
    val sourceAccountId: UUID,
    val targetAccountId: UUID,
    val amount: MoneyInput,
    val date: OffsetDateTime,
    val labels: List<LabelInput>,
    val note: String?
)
