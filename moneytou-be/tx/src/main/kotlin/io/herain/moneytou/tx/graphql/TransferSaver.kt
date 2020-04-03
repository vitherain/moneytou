package io.herain.moneytou.tx.graphql

import io.herain.moneytou.tx.graphql.input.TransferInput
import io.herain.moneytou.tx.graphql.type.Transfer

interface TransferSaver {
    fun saveTransfer(transfer: TransferInput): Transfer
}
