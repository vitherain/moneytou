package io.herain.moneytou.tx.domain

import io.herain.moneytou.common.domain.Currency
import spock.lang.Specification

class PositiveMoneySpec extends Specification {

    def "PositiveMoney can not be created when input is not a positive number"() {
        when:
        new PositiveMoney(input, Currency.CZK)

        then:
        def e = thrown(expectedException)
        e.message == exceptionMessage

        where:
        input || expectedException | exceptionMessage
        new BigDecimal("-0.01") || IllegalArgumentException | "amount=-0.01 must be a positive number"
        new BigDecimal("-1") || IllegalArgumentException | "amount=-1 must be a positive number"
        BigDecimal.ZERO || IllegalArgumentException | "amount=0 must be a positive number"
    }

    def "PositiveMoney can be created when input is a positive number"() {
        when:
        new PositiveMoney(new BigDecimal("0.01"), Currency.CZK)

        then:
        notThrown()
    }
}
