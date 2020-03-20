package io.herain.moneytou.tx.graphql.type

import java.util.UUID

data class TxCategory(
    val id: UUID,
    val name: String,
    val icon: String,
    val childCategories: List<TxCategory>
)
