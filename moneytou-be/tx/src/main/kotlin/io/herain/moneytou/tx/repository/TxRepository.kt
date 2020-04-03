package io.herain.moneytou.tx.repository

import io.herain.moneytou.tx.domain.Tx
import org.springframework.data.repository.Repository
import java.util.UUID

interface TxRepository : Repository<Tx, UUID>, TxFetchingOperations,
    TxPagingOperations, TxSavingOperations
