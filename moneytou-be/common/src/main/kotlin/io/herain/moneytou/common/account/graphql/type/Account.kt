package io.herain.moneytou.common.account.graphql.type

import java.util.UUID

data class Account(
    val id: UUID,
    val name: String,
    val userId: UUID
)
