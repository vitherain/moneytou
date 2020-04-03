package io.herain.moneytou.tx.repository

import io.herain.moneytou.tx.domain.Tx
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TxPagingOperations {

    fun findAll(pageable: Pageable): Page<Tx>
}
