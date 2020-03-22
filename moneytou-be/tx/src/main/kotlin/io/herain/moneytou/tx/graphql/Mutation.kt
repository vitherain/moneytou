package io.herain.moneytou.tx.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import io.herain.moneytou.tx.transaction.domain.Currency
import io.herain.moneytou.tx.transaction.graphql.input.TransferInput
import io.herain.moneytou.tx.transaction.graphql.input.TxInput
import io.herain.moneytou.tx.transaction.graphql.type.Money
import io.herain.moneytou.tx.transaction.graphql.type.Transfer
import io.herain.moneytou.tx.transaction.graphql.type.Tx
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

class Mutation : GraphQLMutationResolver {

    fun saveTx(tx: TxInput): Tx {
        return Tx(
            UUID.randomUUID(),
            Money(BigDecimal.valueOf(1234), Currency.CZK),
            OffsetDateTime.now(),
            UUID.randomUUID(),
            emptySet(),
            UUID.randomUUID(),
            ""
        )
    }

    fun saveTransfer(transfer: TransferInput): Transfer {
        return Transfer(
            UUID.randomUUID(),
            UUID.randomUUID()
        )
    }
}