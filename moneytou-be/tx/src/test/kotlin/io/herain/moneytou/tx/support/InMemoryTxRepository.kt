package io.herain.moneytou.tx.support

import io.herain.moneytou.test.repository.InMemoryCrudRepository
import io.herain.moneytou.tx.domain.Tx
import io.herain.moneytou.tx.repository.TxRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class InMemoryTxRepository(
    existingData: List<Tx>?
) : TxRepository, InMemoryCrudRepository<Tx>(existingData) {

    override fun findAll(pageable: Pageable): Page<Tx> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
