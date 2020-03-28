package io.herain.moneytou.tx.graphql

import io.herain.moneytou.common.account.domain.Account
import io.herain.moneytou.common.shared.exception.MoneytouSecurityException
import io.herain.moneytou.tx.support.InMemoryAccountRepository
import io.herain.moneytou.tx.support.InMemoryTxRepository
import io.herain.moneytou.tx.transaction.domain.Currency
import io.herain.moneytou.tx.transaction.domain.Label
import io.herain.moneytou.tx.transaction.domain.Money
import io.herain.moneytou.tx.transaction.domain.Tx
import io.herain.moneytou.tx.transaction.graphql.input.LabelInput
import io.herain.moneytou.tx.transaction.graphql.input.MoneyInput
import io.herain.moneytou.tx.transaction.graphql.input.TxInput
import io.herain.moneytou.tx.transaction.graphql.type.Label as GqlLabel
import io.herain.moneytou.tx.transaction.graphql.type.Money as GqlMoney
import io.herain.moneytou.tx.transaction.graphql.type.Tx as GqlTx
import spock.lang.Specification

import java.time.OffsetDateTime
import java.util.function.Supplier

class TxSavingMutationSpec extends Specification {

    UUID userId = UUID.randomUUID()
    UUID accountId = UUID.randomUUID()
    Supplier<OffsetDateTime> timeSupplier = { -> OffsetDateTime.parse("2020-03-28T12:56:36Z") }
    Supplier<UUID> currentUserIdSupplier = { -> userId }
    List<Tx> existingTxs = [
            new Tx(
                    UUID.randomUUID(), new Money(new BigDecimal("45.67"), Currency.EUR),
                    timeSupplier.get(), UUID.randomUUID(), [new Label("some label")].toSet(),
                    accountId, "some note"
            )
    ]
    List<Account> existingAccounts = [
            new Account(
                    accountId,
                    new Account.AccountName("Account 1"),
                    Account.AccountStatus.ACTIVE,
                    userId
            )
    ]
    InMemoryTxRepository txRepository
    InMemoryAccountRepository accountRepository
    TxSavingMutation txSavingMutation

    void setup() {
        txRepository = new InMemoryTxRepository(existingTxs)
        accountRepository = new InMemoryAccountRepository(existingAccounts)
        txSavingMutation = new TxSavingMutation(
                txRepository,
                currentUserIdSupplier,
                accountRepository
        )
    }

    def "new Tx is saved"() {
        when:
        final input = new TxInput(
                null, new MoneyInput(new BigDecimal("12.34"), Currency.EUR),
                timeSupplier.get(), UUID.randomUUID(), [new LabelInput("label 1")],
                accountId, "some other note"
        )
        final output = txSavingMutation.saveTx(input)

        then:
        assertTx(output, input)
        txRepository.count() == 2
        Optional<Tx> savedTx = txRepository.findById(output.id)
        assertTx(savedTx, input)
    }

    def "existing Tx is updated"() {
        when:
        final input = new TxInput(
                existingTxs.get(0).id, new MoneyInput(new BigDecimal("12.34"), Currency.EUR),
                timeSupplier.get(), UUID.randomUUID(), [new LabelInput("label 1")],
                accountId, "some other note"
        )
        final output = txSavingMutation.saveTx(input)

        then:
        assertTx(output, input)
        txRepository.count() == 1
        Optional<Tx> savedTx = txRepository.findById(output.id)
        assertTx(savedTx, input)
    }

    def "exception is thrown when Account does not belong to the current user"() {
        when:
        def input = new TxInput(
                null, new MoneyInput(new BigDecimal("12.34"), Currency.EUR),
                timeSupplier.get(), UUID.randomUUID(), [new LabelInput("label 1")],
                UUID.randomUUID(), "some other note"
        )
        txSavingMutation.saveTx(input)

        then:
        def e = thrown(MoneytouSecurityException)
        e.message == "Account with ID=${input.accountId} does not belong to the current user"
    }

    void assertTx(GqlTx tx, TxInput input) {
        assert tx.amount == new GqlMoney(new BigDecimal("12.34"), Currency.EUR)
        assert tx.date == timeSupplier.get()
        assert tx.categoryId == input.categoryId
        assert tx.labels.containsAll([new GqlLabel("label 1")])
        assert tx.accountId == input.accountId
        assert tx.note == "some other note"
    }

    void assertTx(Optional<Tx> tx, TxInput input) {
        assert tx
        assert tx.get().amount == new Money(new BigDecimal("12.34"), Currency.EUR)
        assert tx.get().date == timeSupplier.get()
        assert tx.get().categoryId == input.categoryId
        assert tx.get().labels.containsAll([new Label("label 1")])
        assert tx.get().accountId == input.accountId
        assert tx.get().note == "some other note"
    }
}
