package io.herain.moneytou.dto

import java.time.OffsetDateTime
import java.util.*

data class Income(
        override val id: UUID?,
        override val amount: Money,
        override val date: OffsetDateTime,
        override val category: TransactionCategory,
        override val labels: List<Label>,
        override val account: Account,
        override val note: String?
) : Transaction
