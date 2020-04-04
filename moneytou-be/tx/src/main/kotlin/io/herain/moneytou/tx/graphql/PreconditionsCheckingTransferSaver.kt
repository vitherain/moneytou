package io.herain.moneytou.tx.graphql

import io.herain.moneytou.common.account.repository.AccountCheckOperations
import io.herain.moneytou.common.shared.exception.MoneytouSecurityException
import io.herain.moneytou.tx.graphql.input.TransferInput
import io.herain.moneytou.tx.graphql.type.Transfer
import java.util.UUID
import java.util.function.Supplier

class PreconditionsCheckingTransferSaver(
    private val currentUserIdSupplier: Supplier<UUID>,
    private val accountCheckOperations: AccountCheckOperations,
    private val delegate: TransferSaver
) : TransferSaver {

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
        return delegate.saveTransfer(transfer)
    }
}
