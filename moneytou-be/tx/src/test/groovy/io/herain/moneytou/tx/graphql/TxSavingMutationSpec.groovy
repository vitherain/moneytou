package io.herain.moneytou.tx.graphql


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

    Supplier<OffsetDateTime> timeSupplier = { -> OffsetDateTime.parse("2020-03-28T12:56:36Z") }
    List<Tx> existingData = [
            new Tx(
                    UUID.randomUUID(), new Money(new BigDecimal("45.67"), Currency.EUR),
                    timeSupplier.get(), UUID.randomUUID(), [new Label("some label")].toSet(),
                    UUID.randomUUID(), "some note"
            )
    ]
    InMemoryTxRepository repository = new InMemoryTxRepository(existingData)
    TxSavingMutation txSavingMutation = new TxSavingMutation(repository, repository)

    void setup() {
        repository.deleteAll()
    }

    def "new Tx is saved"() {
        when:
        final input = new TxInput(
                null, new MoneyInput(new BigDecimal("12.34"), Currency.EUR),
                timeSupplier.get(), UUID.randomUUID(), [new LabelInput("label 1")],
                UUID.randomUUID(), "some other note"
        )
        final output = txSavingMutation.saveTx(input)

        then:
        assertTx(output, input)
        repository.count() == 2
        Optional<Tx> savedTx = repository.findById(output.id)
        assertTx(savedTx, input)
    }

    def "existing Tx is updated"() {
        when:
        final input = new TxInput(
                existingData.get(0).id, new MoneyInput(new BigDecimal("12.34"), Currency.EUR),
                timeSupplier.get(), UUID.randomUUID(), [new LabelInput("label 1")],
                UUID.randomUUID(), "some other note"
        )
        final output = txSavingMutation.saveTx(input)

        then:
        assertTx(output, input)
        repository.count() == 1
        Optional<Tx> savedTx = repository.findById(output.id)
        assertTx(savedTx, input)
    }

    void assertTx(GqlTx tx, TxInput input) {
        assert tx.amount == new GqlMoney(new BigDecimal("12.34"), Currency.EUR)
        assert tx.date == timeSupplier.get()
        assert tx.categoryId == input.categoryId
        assert tx.labels == [new GqlLabel("label 1")]
        assert tx.accountId == input.accountId
        assert tx.note == "some other note"
    }

    void assertTx(Optional<Tx> tx, TxInput input) {
        assert tx
        assert tx.get().amount == new Money(new BigDecimal("12.34"), Currency.EUR)
        assert tx.get().date == timeSupplier.get()
        assert tx.get().categoryId == input.categoryId
        assert tx.get().labels == [new Label("label 1")]
        assert tx.get().account.id == input.accountId
        assert tx.get().note == "some other note"
    }
}
