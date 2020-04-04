package io.herain.moneytou.tx.graphql

import io.herain.moneytou.common.account.repository.AccountCheckOperations
import io.herain.moneytou.common.category.repository.CategoryFetchingOperations
import io.herain.moneytou.shared.jpa.support.TransactionExecutor
import io.herain.moneytou.tx.repository.TxSavingOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.UUID
import java.util.function.Supplier

@Configuration
class TransferSavingConfiguration(
    private val transactionExecutor: TransactionExecutor,
    private val currentUserIdSupplier: Supplier<UUID>,
    private val accountCheckOperations: AccountCheckOperations,
    private val categoryFetchingOperations: CategoryFetchingOperations,
    private val txSavingOperations: TxSavingOperations
) {

    @Bean
    fun transferSavingMutation(): TransferSavingMutation {
        return TransferSavingMutation(
            preconditionsCheckingTransferSaver()
        )
    }

    private fun preconditionsCheckingTransferSaver(): PreconditionsCheckingTransferSaver {
        return PreconditionsCheckingTransferSaver(
            currentUserIdSupplier,
            accountCheckOperations,
            databaseTransactionHandlingTransferSaver()
        )
    }

    private fun databaseTransactionHandlingTransferSaver(): DatabaseTransactionHandlingTransferSaver {
        return DatabaseTransactionHandlingTransferSaver(
            transactionExecutor,
            databaseTransferSaver()
        )
    }

    private fun databaseTransferSaver(): DatabaseTransferSaver {
        return DatabaseTransferSaver(
            categoryFetchingOperations,
            txSavingOperations
        )
    }
}
