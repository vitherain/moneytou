package io.herain.moneytou.app.graphql.type

import java.util.*

data class TxCategory(
        val id: UUID,
        val name: String,
        val icon: String,
        val childCategories: List<TxCategory>
)
