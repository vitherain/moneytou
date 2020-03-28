package io.herain.moneytou.tx.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import io.herain.moneytou.tx.transaction.graphql.type.Tx
import io.herain.moneytou.tx.transaction.repository.TxPagingOperations
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

class TxFetchingQuery(
    val txPagingOperations: TxPagingOperations
) : GraphQLQueryResolver {

    private val defaultPageRequest: PageRequest = PageRequest.of(
        0,
        20,
        Sort.by(Sort.Direction.DESC, "date")
    )

    fun transactions(pageRequest: PageRequest = defaultPageRequest): List<Tx> {
        return txPagingOperations.findAll(pageRequest).content.map { it.asDto() }
    }
}
