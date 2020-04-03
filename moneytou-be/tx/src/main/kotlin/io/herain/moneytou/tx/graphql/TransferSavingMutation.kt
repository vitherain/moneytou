package io.herain.moneytou.tx.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import io.herain.moneytou.tx.graphql.input.TransferInput
import io.herain.moneytou.tx.graphql.type.Transfer

class TransferSavingMutation(private val delegate: TransferSaver)
    : TransferSaver, GraphQLMutationResolver {

    override fun saveTransfer(transfer: TransferInput): Transfer {
        return delegate.saveTransfer(transfer)
    }
}
