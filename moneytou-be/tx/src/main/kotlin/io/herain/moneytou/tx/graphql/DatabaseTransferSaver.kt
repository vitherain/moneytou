package io.herain.moneytou.tx.graphql

import io.herain.moneytou.common.category.repository.CategoryFetchingOperations
import io.herain.moneytou.tx.domain.Label
import io.herain.moneytou.tx.domain.Money
import io.herain.moneytou.tx.domain.Tx
import io.herain.moneytou.tx.graphql.input.TransferInput
import io.herain.moneytou.tx.graphql.type.Transfer
import io.herain.moneytou.tx.repository.TxSavingOperations
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Transactional(propagation = Propagation.REQUIRES_NEW)
class DatabaseTransferSaver(
    private val categoryFetchingOperations: CategoryFetchingOperations,
    private val txSavingOperations: TxSavingOperations
) : TransferSaver {

    override fun saveTransfer(transfer: TransferInput): Transfer {
        val categoryId = categoryFetchingOperations.findTransferCategoryId()
        val expensePart = txSavingOperations.save(
            Tx(
                amount = Money(transfer.amount.value.negate(), transfer.amount.currency),
                date = transfer.date,
                categoryId = categoryId,
                labels = transfer.labels.map { Label(it.name) }.toSet(),
                accountId = transfer.sourceAccountId,
                note = transfer.note
            )
        )
        val incomePart = txSavingOperations.save(
            Tx(
                amount = Money(transfer.amount.value, transfer.amount.currency),
                date = transfer.date,
                categoryId = categoryId,
                labels = transfer.labels.map { Label(it.name) }.toSet(),
                accountId = transfer.targetAccountId,
                note = transfer.note
            )
        )
        return Transfer(expensePart.id, incomePart.id)
    }
}
