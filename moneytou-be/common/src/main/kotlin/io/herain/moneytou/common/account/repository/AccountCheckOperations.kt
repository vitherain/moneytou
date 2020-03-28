package io.herain.moneytou.common.account.repository

import java.util.UUID

interface AccountCheckOperations {
    fun existsByIdAndUserId(id: UUID, userId: UUID): Boolean
}
