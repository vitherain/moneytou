package io.herain.moneytou.tx.transaction.repository

import io.herain.moneytou.tx.transaction.domain.Tx
import java.util.Optional
import java.util.UUID

interface TxFetchingOperations {
    fun findById(id: UUID): Optional<Tx>
}
