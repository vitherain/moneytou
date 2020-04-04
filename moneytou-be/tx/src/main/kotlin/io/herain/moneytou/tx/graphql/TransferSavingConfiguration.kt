package io.herain.moneytou.tx.graphql

import io.herain.moneytou.common.category.repository.CategoryFetchingOperations
import io.herain.moneytou.shared.jpa.support.TransactionExecutor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TransferSavingConfiguration(
    private val transactionExecutor: TransactionExecutor,
    private val categoryFetchingOperations: CategoryFetchingOperations,
    private val txSaver: TxSaver
) {

    @Bean
    fun transferSavingMutation(): TransferSavingMutation {
        return TransferSavingMutation(
            databaseTransactionHandlingTransferSaver()
        )
    }

    private fun databaseTransactionHandlingTransferSaver(): DatabaseTransactionHandlingTransferSaver {
        return DatabaseTransactionHandlingTransferSaver(
            transactionExecutor,
            txsDefiningTransferSavingRouter()
        )
    }

    private fun txsDefiningTransferSavingRouter(): TxsDefiningTransferSavingRouter {
        return TxsDefiningTransferSavingRouter(
            categoryFetchingOperations,
            txSaver
        )
    }
}
