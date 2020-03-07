package io.herain.moneytou.app.graphql.inputs

import java.time.OffsetDateTime
import java.util.*

data class TransferInput(
    val sourceAccountId: UUID,
    val targetAccountId: UUID,
    val amount: MoneyInput,
    val date: OffsetDateTime,
    val labels: List<LabelInput>,
    val note: String?
)
