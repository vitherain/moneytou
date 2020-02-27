package io.herain.moneytou.app.dto

import java.util.*

data class TransactionCategory(
        val id: UUID?,
        val name: String,
        val icon: String,
        val childCategories: List<TransactionCategory>
)
