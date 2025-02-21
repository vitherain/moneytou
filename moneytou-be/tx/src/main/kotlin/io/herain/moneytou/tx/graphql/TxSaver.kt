package io.herain.moneytou.tx.graphql

import io.herain.moneytou.tx.graphql.input.TxInput
import io.herain.moneytou.tx.graphql.type.Tx

interface TxSaver {
    fun saveTx(txInput: TxInput): Tx
}
