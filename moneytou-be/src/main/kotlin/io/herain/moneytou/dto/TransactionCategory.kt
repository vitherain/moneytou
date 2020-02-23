package io.herain.moneytou.dto

import java.util.*

data class TransactionCategory(
        val id: UUID?,
        val name: String,
        val icon: String,
        val childCategories: List<TransactionCategory>
)
