package io.herain.moneytou.tx.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import io.herain.moneytou.common.account.repository.AccountCheckOperations
import io.herain.moneytou.common.category.repository.CategoryFetchingOperations
import io.herain.moneytou.common.shared.exception.MoneytouSecurityException
import io.herain.moneytou.tx.domain.Label
import io.herain.moneytou.tx.domain.Money
import io.herain.moneytou.tx.domain.Tx
import io.herain.moneytou.tx.graphql.input.TransferInput
import io.herain.moneytou.tx.graphql.type.Transfer
import io.herain.moneytou.tx.repository.TxSavingOperations
import java.util.UUID
import java.util.function.Supplier

class TransferSavingMutation(
    private val currentUserIdSupplier: Supplier<UUID>,
    private val accountCheckOperations: AccountCheckOperations,
    private val categoryFetchingOperations: CategoryFetchingOperations,
    private val txSavingOperations: TxSavingOperations
) : TransferSaver, GraphQLMutationResolver {

    override fun saveTransfer(transfer: TransferInput): Transfer {
        if (!accountCheckOperations.existsByIdAndUserId(transfer.sourceAccountId, currentUserIdSupplier.get())) {
            throw MoneytouSecurityException(
                "Account with ID=${transfer.sourceAccountId} does not belong to the current user"
            )
        }
        if (!accountCheckOperations.existsByIdAndUserId(transfer.targetAccountId, currentUserIdSupplier.get())) {
            throw MoneytouSecurityException(
                "Account with ID=${transfer.targetAccountId} does not belong to the current user"
            )
        }
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
