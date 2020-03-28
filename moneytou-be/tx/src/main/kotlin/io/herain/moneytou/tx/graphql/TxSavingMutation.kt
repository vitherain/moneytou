package io.herain.moneytou.tx.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import io.herain.moneytou.common.account.repository.AccountCheckOperations
import io.herain.moneytou.common.shared.exception.MoneytouSecurityException
import io.herain.moneytou.tx.transaction.domain.Label
import io.herain.moneytou.tx.transaction.domain.Money
import io.herain.moneytou.tx.transaction.graphql.input.TxInput
import io.herain.moneytou.tx.transaction.graphql.type.Tx
import io.herain.moneytou.tx.transaction.repository.TxSavingOperations
import java.util.UUID
import java.util.function.Supplier
import io.herain.moneytou.tx.transaction.domain.Tx as DomainTx

class TxSavingMutation(
    private val savingOperations: TxSavingOperations,
    private val currentUserIdSupplier: Supplier<UUID>,
    private val accountCheckOperations: AccountCheckOperations
) : GraphQLMutationResolver {

    fun saveTx(txInput: TxInput): Tx {
        if (!accountCheckOperations.existsByIdAndUserId(txInput.accountId, currentUserIdSupplier.get())) {
            throw MoneytouSecurityException(
                "Account with ID=${txInput.accountId} does not belong to the current user"
            )
        }

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
