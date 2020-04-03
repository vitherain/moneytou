package io.herain.moneytou.tx.repository

import io.herain.moneytou.tx.domain.Tx
import java.util.Optional
import java.util.UUID

interface TxFetchingOperations {
    fun findById(id: UUID): Optional<Tx>
}
