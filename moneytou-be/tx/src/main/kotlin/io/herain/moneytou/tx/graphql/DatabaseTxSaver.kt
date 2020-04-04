package io.herain.moneytou.tx.graphql

import io.herain.moneytou.tx.domain.Label
import io.herain.moneytou.tx.domain.Money
import io.herain.moneytou.tx.graphql.input.TxInput
import io.herain.moneytou.tx.graphql.type.Tx
import io.herain.moneytou.tx.repository.TxSavingOperations
import io.herain.moneytou.tx.domain.Tx as DomainTx

class DatabaseTxSaver(
    private val savingOperations: TxSavingOperations
) : TxSaver {

    override fun saveTx(txInput: TxInput): Tx {
        return if (txInput.id != null) {
            savingOperations.save(
                DomainTx(
                    txInput.id,
                    Money(txInput.amount.value, txInput.amount.currency),
                    txInput.date,
                    txInput.categoryId,
                    txInput.labels.map { Label(it.name) }.toSet(),
                    txInput.accountId,
                    txInput.note
                )
            ).asDto()
        } else {
            savingOperations.save(
                DomainTx(
                    amount = Money(txInput.amount.value, txInput.amount.currency),
                    date = txInput.date,
                    categoryId = txInput.categoryId,
                    labels = txInput.labels.map { Label(it.name) }.toSet(),
                    accountId = txInput.accountId,
                    note = txInput.note
                )
            ).asDto()
        }
    }
}
