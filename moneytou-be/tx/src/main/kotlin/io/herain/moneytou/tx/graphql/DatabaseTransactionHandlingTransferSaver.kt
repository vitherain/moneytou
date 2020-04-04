package io.herain.moneytou.tx.graphql

import io.herain.moneytou.shared.jpa.support.TransactionExecutor
import io.herain.moneytou.tx.graphql.input.TransferInput
import io.herain.moneytou.tx.graphql.type.Transfer

class DatabaseTransactionHandlingTransferSaver(
    private val transactionExecutor: TransactionExecutor,
    private val delegate: TransferSaver
) : TransferSaver {

    override fun saveTransfer(transfer: TransferInput): Transfer {
        return transactionExecutor.execute<Transfer> {
            delegate.saveTransfer(transfer)
        }
    }
}
