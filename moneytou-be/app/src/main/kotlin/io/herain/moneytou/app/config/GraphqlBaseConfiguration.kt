package io.herain.moneytou.app.config

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import io.herain.moneytou.tx.graphql.TransferSavingMutation
import io.herain.moneytou.tx.graphql.TxFetchingQuery
import io.herain.moneytou.tx.transaction.repository.TxPagingOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GraphqlBaseConfiguration {

    @Bean
    fun query(): GraphQLQueryResolver {
        return object : GraphQLQueryResolver {
            /* this is to avoid runtime error as root Query must exist in app context */
        }
    }

    @Bean
    fun mutation(): GraphQLMutationResolver {
        return object : GraphQLMutationResolver {
            /* this is to avoid runtime error as root Mutation must exist in app context */
        }
    }

    @Bean
    fun transferSavingMutation(): TransferSavingMutation {
        return TransferSavingMutation()
    }

    @Bean
    fun txFetchingQuery(txPagingOperations: TxPagingOperations): TxFetchingQuery {
        return TxFetchingQuery(txPagingOperations)
    }
}
