package io.herain.moneytou.tx.transaction.repository

import io.herain.moneytou.tx.transaction.domain.Tx
import org.springframework.data.repository.Repository
import java.util.UUID

interface TxRepository : Repository<Tx, UUID>, TxPagingOperations
