package io.herain.moneytou.tx.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import io.herain.moneytou.common.domain.Currency
import io.herain.moneytou.tx.graphql.input.ExpenseInput
import io.herain.moneytou.tx.graphql.input.IncomeInput
import io.herain.moneytou.tx.graphql.input.TransferInput
import io.herain.moneytou.tx.graphql.type.Account
import io.herain.moneytou.tx.graphql.type.Expense
import io.herain.moneytou.tx.graphql.type.Income
import io.herain.moneytou.tx.graphql.type.Money
import io.herain.moneytou.tx.graphql.type.Transfer
import io.herain.moneytou.tx.graphql.type.TxCategory
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

class Mutation : GraphQLMutationResolver {

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