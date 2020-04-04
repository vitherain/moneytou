package io.herain.moneytou.tx.graphql

import io.herain.moneytou.shared.jpa.support.TransactionExecutor
import io.herain.moneytou.tx.graphql.input.TxInput
import io.herain.moneytou.tx.graphql.type.Tx

class DatabaseTransactionHandlingTxSaver(
    private val transactionExecutor: TransactionExecutor,
    private val delegate: TxSaver
) : TxSaver {

    override fun saveTx(txInput: TxInput): Tx {
        return transactionExecutor.execute<Tx> {
            delegate.saveTx(txInput)
        }
    }
}
