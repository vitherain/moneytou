package io.herain.moneytou.shared.repository

interface SimpleFetchingOperation<T, ID> {
    fun findById(id: ID): T?
}
