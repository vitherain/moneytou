package io.herain.moneytou.app.graphql.type

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import io.herain.moneytou.app.domain.Currency
import io.herain.moneytou.app.graphql.inputs.ExpenseInput
import io.herain.moneytou.app.graphql.inputs.IncomeInput
import io.herain.moneytou.app.graphql.inputs.TransferInput
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

class Mutation() : GraphQLMutationResolver {

    fun saveExpense(expense: ExpenseInput): Expense {
        return Expense(
            UUID.randomUUID(),
            Money(BigDecimal.valueOf(1234), Currency.CZK),
            OffsetDateTime.now(),
            TxCategory(
                UUID.randomUUID(), "", "", emptyList()
            ),
            emptyList(),
            Account(UUID.randomUUID(), ""),
            ""
        )
    }

    fun saveIncome(income: IncomeInput): Income {
        return Income(
            UUID.randomUUID(),
            Money(BigDecimal.valueOf(-1234), Currency.CZK),
            OffsetDateTime.now(),
            TxCategory(
                UUID.randomUUID(), "", "", emptyList()
            ),
            emptyList(),
            Account(UUID.randomUUID(), ""),
            ""
        )
    }

    fun saveTransfer(transfer: TransferInput): Transfer {
        return Transfer(
            UUID.randomUUID(),
            Expense(
                UUID.randomUUID(),
                Money(BigDecimal.valueOf(1234), Currency.CZK),
                OffsetDateTime.now(),
                TxCategory(
                    UUID.randomUUID(), "", "", emptyList()
                ),
                emptyList(),
                Account(UUID.randomUUID(), ""),
                ""
            ),
            Income(
                UUID.randomUUID(),
                Money(BigDecimal.valueOf(-1234), Currency.CZK),
                OffsetDateTime.now(),
                TxCategory(
                    UUID.randomUUID(), "", "", emptyList()
                ),
                emptyList(),
                Account(UUID.randomUUID(), ""),
                ""
            )
        )
    }
}