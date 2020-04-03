package io.herain.moneytou.tx.graphql

import io.herain.moneytou.common.account.domain.Account
import io.herain.moneytou.common.shared.exception.MoneytouSecurityException
import io.herain.moneytou.tx.domain.Currency
import io.herain.moneytou.tx.domain.Label
import io.herain.moneytou.tx.domain.Money
import io.herain.moneytou.tx.domain.Tx
import io.herain.moneytou.tx.graphql.TxSavingConfiguration
import io.herain.moneytou.tx.graphql.TxSavingMutation
import io.herain.moneytou.tx.graphql.input.LabelInput
import io.herain.moneytou.tx.graphql.input.MoneyInput
import io.herain.moneytou.tx.graphql.input.TxInput
import io.herain.moneytou.tx.graphql.type.Label as GqlLabel
import io.herain.moneytou.tx.graphql.type.Money as GqlMoney
import io.herain.moneytou.tx.graphql.type.Tx as GqlTx
import io.herain.moneytou.tx.support.InMemoryAccountRepository
import io.herain.moneytou.tx.support.InMemoryTxRepository
import spock.lang.Specification

import java.time.OffsetDateTime
import java.util.function.Supplier

class TxSavingMutationSpec extends Specification {

    UUID userId = UUID.randomUUID()
    UUID accountId = UUID.randomUUID()
    OffsetDateTime now = OffsetDateTime.parse("2020-03-28T12:56:36Z")
    Supplier<UUID> currentUserIdSupplier = { -> userId }
    List<Tx> existingTxs = [
            new Tx(
                    UUID.randomUUID(), new Money(new BigDecimal("45.67"), Currency.EUR),
                    now, UUID.randomUUID(), [new Label("some label")].toSet(),
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
        txSavingMutation = new TxSavingConfiguration(
                currentUserIdSupplier,
                accountRepository,
                txRepository
        ).txSavingMutation()
    }

    def "new Tx is saved"() {
        when:
        final input = new TxInput(
                null, new MoneyInput(new BigDecimal("12.34"), Currency.EUR),
                now, UUID.randomUUID(), [new LabelInput("label 1")],
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
                now, UUID.randomUUID(), [new LabelInput("label 1")],
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
                now, UUID.randomUUID(), [new LabelInput("label 1")],
                UUID.randomUUID(), "some other note"
        )
        txSavingMutation.saveTx(input)

        then:
        def e = thrown(MoneytouSecurityException)
        e.message == "Account with ID=${input.accountId} does not belong to the current user"
        txRepository.count() == 1
    }

    void assertTx(final GqlTx tx, final TxInput input) {
        assert tx.amount == new GqlMoney(new BigDecimal("12.34"), Currency.EUR)
        assert tx.date == now
        assert tx.categoryId == input.categoryId
        assert tx.labels.containsAll([new GqlLabel("label 1")])
        assert tx.accountId == input.accountId
        assert tx.note == "some other note"
    }

    void assertTx(final Optional<Tx> tx, final TxInput input) {
        assert tx
        assert tx.get().amount == new Money(new BigDecimal("12.34"), Currency.EUR)
        assert tx.get().date == now
        assert tx.get().categoryId == input.categoryId
        assert tx.get().labels.containsAll([new Label("label 1")])
        assert tx.get().accountId == input.accountId
        assert tx.get().note == "some other note"
    }
}
