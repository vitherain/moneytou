package io.herain.moneytou.tx.graphql

import io.herain.moneytou.tx.repository.TxPagingOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TxFetchingConfiguration(private val txPagingOperations: TxPagingOperations) {

    @Bean
    fun txFetchingQuery(): TxFetchingQuery {
        return TxFetchingQuery(txPagingOperations)
    }
}
