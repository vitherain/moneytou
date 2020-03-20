package io.herain.moneytou.tx.graphql.type

import io.herain.moneytou.tx.domain.PositiveMoney
import java.time.OffsetDateTime
import java.util.UUID

data class Expense(
    override val id: UUID,
    override val amount: PositiveMoney,
    override val date: OffsetDateTime,
    override val category: TxCategory,
    override val labels: List<Label>,
    override val account: Account,
    override val note: String?
) : Tx
