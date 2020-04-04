package io.herain.moneytou.tx.graphql

import io.herain.moneytou.common.category.repository.CategoryFetchingOperations
import io.herain.moneytou.tx.graphql.input.TransferInput
import io.herain.moneytou.tx.graphql.input.TxInput
import io.herain.moneytou.tx.graphql.type.Transfer

class TxsDefiningTransferSavingRouter(
    private val categoryFetchingOperations: CategoryFetchingOperations,
    private val txSaver: TxSaver
) : TransferSaver {

    override fun saveTransfer(transfer: TransferInput): Transfer {
        val categoryId = categoryFetchingOperations.findTransferCategoryId()
        val (expensePartId) = txSaver.saveTx(
            TxInput(
                null,
                transfer.amount.negated(),
                transfer.date,
                categoryId,
                transfer.labels,
                transfer.sourceAccountId,
                transfer.note
            )
        )
        val (incomePartId) = txSaver.saveTx(
            TxInput(
                null,
                transfer.amount,
                transfer.date,
                categoryId,
                transfer.labels,
                transfer.targetAccountId,
                transfer.note
            )
        )
        return Transfer(expensePartId, incomePartId)
    }
}
