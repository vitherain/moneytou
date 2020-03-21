package io.herain.moneytou.tx.transaction.graphql.type

import io.herain.moneytou.tx.graphql.type.Account
import io.herain.moneytou.tx.graphql.type.TxCategory
import io.herain.moneytou.tx.transaction.domain.PositiveMoney
import java.time.OffsetDateTime
import java.util.UUID

data class Income(
    override val id: UUID,
    override val amount: PositiveMoney,
    override val date: OffsetDateTime,
    override val category: TxCategory,
    override val labels: List<Label>,
    override val account: Account,
    override val note: String?
) : Tx
