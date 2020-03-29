package io.herain.moneytou.tx.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import io.herain.moneytou.tx.transaction.graphql.input.TxInput
import io.herain.moneytou.tx.transaction.graphql.type.Tx

class TxSavingMutation(private val delegate: TxSaver) : TxSaver, GraphQLMutationResolver {

    override fun saveTx(txInput: TxInput): Tx {
        return delegate.saveTx(txInput)
    }
}
