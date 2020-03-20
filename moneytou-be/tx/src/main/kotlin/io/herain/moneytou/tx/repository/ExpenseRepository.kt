package io.herain.moneytou.tx.repository

import io.herain.moneytou.tx.graphql.type.Expense
import org.springframework.data.repository.Repository
import java.util.UUID

interface ExpenseRepository : Repository<Expense, UUID> {

    fun findOne(id: UUID): Expense?
}
