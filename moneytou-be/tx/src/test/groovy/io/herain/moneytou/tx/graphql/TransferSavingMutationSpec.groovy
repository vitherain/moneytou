package io.herain.moneytou.tx.graphql

import io.herain.moneytou.common.account.domain.Account
import io.herain.moneytou.common.category.domain.Category
import io.herain.moneytou.common.shared.exception.MoneytouSecurityException
import io.herain.moneytou.test.jpa.support.TransactionExecutorFake
import io.herain.moneytou.tx.domain.Currency
import io.herain.moneytou.tx.domain.Label
import io.herain.moneytou.tx.domain.Money
import io.herain.moneytou.tx.domain.Tx
import io.herain.moneytou.tx.graphql.input.LabelInput
import io.herain.moneytou.tx.graphql.input.MoneyInput
import io.herain.moneytou.tx.graphql.input.TransferInput
import io.herain.moneytou.tx.support.InMemoryAccountRepository
import io.herain.moneytou.tx.support.InMemoryCategoryRepository
import io.herain.moneytou.tx.support.InMemoryTxRepository
import spock.lang.Specification

import java.time.OffsetDateTime
import java.util.function.Supplier

class TransferSavingMutationSpec extends Specification {

    UUID userId = UUID.randomUUID()
    UUID sourceAccountId = UUID.randomUUID()
    UUID targetAccountId = UUID.randomUUID()
    UUID transferCategoryId = UUID.randomUUID()
    OffsetDateTime now = OffsetDateTime.parse("2020-03-28T12:56:36Z")
    Supplier<UUID> currentUserIdSupplier = { -> userId }
    List<Tx> existingTxs = [
            new Tx(
                    UUID.randomUUID(), new Money(new BigDecimal("45.67"), Currency.EUR),
                    now, UUID.randomUUID(), [new Label("some label")].toSet(),
                    sourceAccountId, "some note"
            ),
            new Tx(
                    UUID.randomUUID(), new Money(new BigDecimal("78.12"), Currency.EUR),
                    now, UUID.randomUUID(), [new Label("some other label")].toSet(),
                    targetAccountId, "some other note"
            )
    ]
    List<Account> existingAccounts = [
            new Account(
                    sourceAccountId,
                    new Account.AccountName("Account 1"),
                    Account.AccountStatus.ACTIVE,
                    userId
            ),
            new Account(
                    targetAccountId,
                    new Account.AccountName("Account 2"),
                    Account.AccountStatus.ACTIVE,
                    userId
            )
    ]
    List<Category> existingCategories = [
            new Category(
                    transferCategoryId,
                    new Category.CategoryCode(Category.TRANSFER_CODE),
                    Category.CategoryStatus.ACTIVE,
                    Category.Icon.EXCHANGE_ALT,
                    []
            )
    ]
    InMemoryTxRepository txRepository
    InMemoryAccountRepository accountRepository
    InMemoryCategoryRepository categoryRepository
    TransferSavingMutation transferSavingMutation

    void setup() {
        txRepository = new InMemoryTxRepository(existingTxs)
        accountRepository = new InMemoryAccountRepository(existingAccounts)
        categoryRepository = new InMemoryCategoryRepository(existingCategories)
        transferSavingMutation = new TransferSavingConfiguration(
                new TransactionExecutorFake(),
                categoryRepository,
                new TxSavingConfiguration(
                        new TransactionExecutorFake(),
                        currentUserIdSupplier,
                        accountRepository,
                        txRepository
                ).txSavingMutation()
        ).transferSavingMutation()
    }

    def "new Transfer is saved"() {
        when:
        def input = new TransferInput(
                sourceAccountId, targetAccountId, new MoneyInput(new BigDecimal("12.34"), Currency.EUR),
                now, [new LabelInput("label 1"), new LabelInput("label 2")], "some other note"
        )
        final output = transferSavingMutation.saveTransfer(input)

        then:
        txRepository.count() == 4
        Tx savedExpense = txRepository.findById(output.expensePartId)
        Tx savedIncome = txRepository.findById(output.incomePartId)
        assertExpense(savedExpense)
        assertIncome(savedIncome)
    }

    def "exception is thrown when source account does not belong to the current user"() {
        when:
        def input = new TransferInput(
                UUID.randomUUID(), targetAccountId, new MoneyInput(new BigDecimal("12.34"), Currency.EUR),
                now, [new LabelInput("label 1"), new LabelInput("label 2")], "some other note"
        )
        transferSavingMutation.saveTransfer(input)

        then:
        def e = thrown(MoneytouSecurityException)
        e.message == "Account with ID=${input.sourceAccountId} does not belong to the current user"
        txRepository.count() != 4
    }

    def "exception is thrown when target account does not belong to the current user"() {
        when:
        def input = new TransferInput(
                sourceAccountId, UUID.randomUUID(), new MoneyInput(new BigDecimal("12.34"), Currency.EUR),
                now, [new LabelInput("label 1"), new LabelInput("label 2")], "some other note"
        )
        transferSavingMutation.saveTransfer(input)

        then:
        def e = thrown(MoneytouSecurityException)
        e.message == "Account with ID=${input.targetAccountId} does not belong to the current user"
        txRepository.count() != 4
    }

    void assertExpense(final Tx expense) {
        assert expense.amount == new Money(new BigDecimal("-12.34"), Currency.EUR)
        assert expense.date == now
        assert expense.categoryId == transferCategoryId
        assert expense.labels.containsAll([new Label("label 1"), new Label("label 2")])
        assert expense.accountId == sourceAccountId
        assert expense.note == "some other note"
    }

    void assertIncome(final Tx income) {
        assert income.amount == new Money(new BigDecimal("12.34"), Currency.EUR)
        assert income.date == now
        assert income.categoryId == transferCategoryId
        assert income.labels.containsAll([new Label("label 1"), new Label("label 2")])
        assert income.accountId == targetAccountId
        assert income.note == "some other note"
    }
}
