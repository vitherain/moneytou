package io.herain.moneytou.tx.graphql

import io.herain.moneytou.common.account.repository.AccountCheckOperations
import io.herain.moneytou.shared.jpa.support.TransactionExecutor
import io.herain.moneytou.tx.repository.TxSavingOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.UUID
import java.util.function.Supplier

@Configuration
class TxSavingConfiguration(
    private val transactionExecutor: TransactionExecutor,
    private val currentUserIdSupplier: Supplier<UUID>,
    private val accountCheckOperations: AccountCheckOperations,
    private val txSavingOperations: TxSavingOperations
) {

    @Bean
    fun txSavingMutation(): TxSavingMutation {
        return TxSavingMutation(preconditionsCheckingTxSaver())
    }

    private fun preconditionsCheckingTxSaver(): PreconditionsCheckingTxSaver {
        return PreconditionsCheckingTxSaver(
            transactionExecutor,
            currentUserIdSupplier,
            accountCheckOperations,
            databaseTxSaver()
        )
    }

    private fun databaseTxSaver(): DatabaseTxSaver {
        return DatabaseTxSaver(
            transactionExecutor,
            txSavingOperations
        )
    }
}
