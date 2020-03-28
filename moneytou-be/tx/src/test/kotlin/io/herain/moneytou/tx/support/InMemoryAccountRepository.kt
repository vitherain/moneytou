package io.herain.moneytou.tx.support

import io.herain.moneytou.common.account.domain.Account
import io.herain.moneytou.common.account.repository.AccountRepository
import io.herain.moneytou.test.repository.InMemoryCrudRepository
import java.util.UUID

class InMemoryAccountRepository(
    existingData: List<Account>?
) : AccountRepository, InMemoryCrudRepository<Account>(existingData) {

    override fun existsByIdAndUserId(id: UUID, userId: UUID): Boolean {
        return db.any { it.key == id && it.value.userId == userId }
    }
}
