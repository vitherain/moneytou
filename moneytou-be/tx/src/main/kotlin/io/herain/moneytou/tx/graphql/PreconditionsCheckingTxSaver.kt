package io.herain.moneytou.tx.graphql

import io.herain.moneytou.common.account.repository.AccountCheckOperations
import io.herain.moneytou.common.shared.exception.MoneytouSecurityException
import io.herain.moneytou.tx.graphql.input.TxInput
import io.herain.moneytou.tx.graphql.type.Tx
import java.util.UUID
import java.util.function.Supplier

class PreconditionsCheckingTxSaver(
    private val currentUserIdSupplier: Supplier<UUID>,
    private val accountCheckOperations: AccountCheckOperations,
    private val delegate: TxSaver
) : TxSaver {

    override fun saveTx(txInput: TxInput): Tx {
        if (!accountCheckOperations.existsByIdAndUserId(txInput.accountId, currentUserIdSupplier.get())) {
            throw MoneytouSecurityException(
                "Account with ID=${txInput.accountId} does not belong to the current user"
            )
        }
        return delegate.saveTx(txInput)
    }
}
