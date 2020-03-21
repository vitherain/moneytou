package io.herain.moneytou.tx.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import io.herain.moneytou.common.domain.Currency
import io.herain.moneytou.tx.graphql.type.Account
import io.herain.moneytou.tx.graphql.type.TxCategory
import io.herain.moneytou.tx.transaction.domain.PositiveMoney
import io.herain.moneytou.tx.transaction.graphql.input.ExpenseInput
import io.herain.moneytou.tx.transaction.graphql.input.IncomeInput
import io.herain.moneytou.tx.transaction.graphql.input.TransferInput
import io.herain.moneytou.tx.transaction.graphql.type.Expense
import io.herain.moneytou.tx.transaction.graphql.type.Income
import io.herain.moneytou.tx.transaction.graphql.type.Transfer
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

class Mutation : GraphQLMutationResolver {

    fun saveExpense(expense: ExpenseInput): Expense {
        return Expense(
            UUID.randomUUID(),
            PositiveMoney(BigDecimal.valueOf(1234), Currency.CZK),
            OffsetDateTime.now(),
            TxCategory(
                UUID.randomUUID(), "", "", emptyList()
            ),
            emptyList(),
            Account(UUID.randomUUID(), "", UUID.randomUUID()),
            ""
        )
    }

    fun saveIncome(income: IncomeInput): Income {
        return Income(
            UUID.randomUUID(),
            PositiveMoney(BigDecimal.valueOf(-1234), Currency.CZK),
            OffsetDateTime.now(),
            TxCategory(
                UUID.randomUUID(), "", "", emptyList()
            ),
            emptyList(),
            Account(UUID.randomUUID(), "", UUID.randomUUID()),
            ""
        )
    }

    fun saveTransfer(transfer: TransferInput): Transfer {
        return Transfer(
            UUID.randomUUID(),
            Expense(
                UUID.randomUUID(),
                PositiveMoney(BigDecimal.valueOf(1234), Currency.CZK),
                OffsetDateTime.now(),
                TxCategory(
                    UUID.randomUUID(), "", "", emptyList()
                ),
                emptyList(),
                Account(UUID.randomUUID(), "", UUID.randomUUID()),
                ""
            ),
            Income(
                UUID.randomUUID(),
                PositiveMoney(BigDecimal.valueOf(-1234), Currency.CZK),
                OffsetDateTime.now(),
                TxCategory(
                    UUID.randomUUID(), "", "", emptyList()
                ),
                emptyList(),
                Account(UUID.randomUUID(), "", UUID.randomUUID()),
                ""
            )
        )
    }
}