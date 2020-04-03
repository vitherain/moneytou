package io.herain.moneytou.tx.graphql

import io.herain.moneytou.common.account.repository.AccountCheckOperations
import io.herain.moneytou.common.category.repository.CategoryFetchingOperations
import io.herain.moneytou.tx.repository.TxSavingOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.UUID
import java.util.function.Supplier

@Configuration
class TransferSavingConfiguration(
    private val currentUserIdSupplier: Supplier<UUID>,
    private val accountCheckOperations: AccountCheckOperations,
    private val categoryFetchingOperations: CategoryFetchingOperations,
    private val txSavingOperations: TxSavingOperations
) {

    @Bean
    fun databaseTransferSaver(): DatabaseTransferSaver {
        return DatabaseTransferSaver(
            categoryFetchingOperations,
            txSavingOperations
        )
    }

    @Bean
    fun preconditionsCheckingTransferSaver(): PreconditionsCheckingTransferSaver {
        return PreconditionsCheckingTransferSaver(
            currentUserIdSupplier,
            accountCheckOperations,
            databaseTransferSaver()
        )
    }

    @Bean
    fun transferSavingMutation(): TransferSavingMutation {
        return TransferSavingMutation(
            preconditionsCheckingTransferSaver()
        )
    }
}
