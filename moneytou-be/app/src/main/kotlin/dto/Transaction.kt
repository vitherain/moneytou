package io.herain.moneytou.app.dto

import java.time.OffsetDateTime
import java.util.*

interface Transaction {
    val id: UUID?
    val amount: Money
    val date: OffsetDateTime
    val category: TransactionCategory
    val labels: List<Label>
    val account: Account
    val note: String?
}
