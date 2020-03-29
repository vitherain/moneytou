package io.herain.moneytou.tx.graphql

import io.herain.moneytou.common.account.repository.AccountCheckOperations
import io.herain.moneytou.tx.transaction.repository.TxSavingOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.UUID
import java.util.function.Supplier

@Configuration
class TxSavingConfiguration(
    private val currentUserIdSupplier: Supplier<UUID>,
    private val accountCheckOperations: AccountCheckOperations,
    private val txSavingOperations: TxSavingOperations) {

    @Bean
    fun databaseTxSaver(): DatabaseTxSaver {
        return DatabaseTxSaver(txSavingOperations)
    }

    @Bean
    fun preconditionsCheckingTxSaver(): PreconditionsCheckingTxSaver {
        return PreconditionsCheckingTxSaver(
            currentUserIdSupplier,
            accountCheckOperations,
            databaseTxSaver()
        )
    }

    @Bean
    fun txSavingMutation(): TxSavingMutation {
        return TxSavingMutation(preconditionsCheckingTxSaver())
    }
}
