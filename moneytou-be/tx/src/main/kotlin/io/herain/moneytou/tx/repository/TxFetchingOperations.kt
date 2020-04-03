package io.herain.moneytou.tx.repository

import io.herain.moneytou.tx.domain.Tx
import java.util.UUID

interface TxFetchingOperations {
    fun findById(id: UUID): Tx?
}
