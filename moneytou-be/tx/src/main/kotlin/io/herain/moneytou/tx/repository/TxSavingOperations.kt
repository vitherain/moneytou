package io.herain.moneytou.tx.repository

import io.herain.moneytou.tx.domain.Tx

interface TxSavingOperations {
    fun <S : Tx> save(entity: S): S
}
