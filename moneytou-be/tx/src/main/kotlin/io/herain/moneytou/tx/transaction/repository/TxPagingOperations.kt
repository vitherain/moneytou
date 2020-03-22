package io.herain.moneytou.tx.transaction.repository

import io.herain.moneytou.tx.transaction.domain.Tx
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TxPagingOperations {

    fun findAll(pageable: Pageable): Page<Tx>
}
