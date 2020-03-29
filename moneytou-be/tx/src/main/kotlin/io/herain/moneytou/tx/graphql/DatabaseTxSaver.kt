package io.herain.moneytou.tx.graphql

import io.herain.moneytou.tx.transaction.domain.Label
import io.herain.moneytou.tx.transaction.domain.Money
import io.herain.moneytou.tx.transaction.graphql.input.TxInput
import io.herain.moneytou.tx.transaction.graphql.type.Tx
import io.herain.moneytou.tx.transaction.repository.TxSavingOperations
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import io.herain.moneytou.tx.transaction.domain.Tx as DomainTx

@Transactional(propagation = Propagation.REQUIRES_NEW)
class DatabaseTxSaver(private val savingOperations: TxSavingOperations) : TxSaver {

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
