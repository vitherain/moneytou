package io.herain.moneytou.tx.graphql.type

import java.time.OffsetDateTime
import java.util.UUID

data class Income(
    override val id: UUID,
    override val amount: Money,
    override val date: OffsetDateTime,
    override val category: TxCategory,
    override val labels: List<Label>,
    override val account: Account,
    override val note: String?
) : Tx
