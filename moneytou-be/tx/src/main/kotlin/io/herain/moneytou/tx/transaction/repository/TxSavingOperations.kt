package io.herain.moneytou.tx.transaction.repository

import io.herain.moneytou.tx.transaction.domain.Tx

interface TxSavingOperations {
    fun <S : Tx> save(entity: S): S
}
