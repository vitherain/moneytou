package io.herain.moneytou.tx.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import io.herain.moneytou.tx.transaction.graphql.input.TransferInput
import io.herain.moneytou.tx.transaction.graphql.type.Transfer
import java.util.UUID

class TransferSavingMutation : GraphQLMutationResolver {

    fun saveTransfer(transfer: TransferInput): Transfer {
        return Transfer(
            UUID.randomUUID(),
            UUID.randomUUID()
        )
    }
}
