package graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import graphql.input.ExpenseInput
import graphql.input.IncomeInput
import graphql.input.TransferInput
import io.herain.moneytou.app.domain.Currency
import io.herain.moneytou.app.graphql.type.Account
import io.herain.moneytou.app.graphql.type.Expense
import io.herain.moneytou.app.graphql.type.Income
import io.herain.moneytou.app.graphql.type.Money
import io.herain.moneytou.app.graphql.type.Transfer
import io.herain.moneytou.app.graphql.type.TxCategory
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

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