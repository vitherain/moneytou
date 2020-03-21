package io.herain.moneytou.tx.transaction.domain


import spock.lang.Specification

class NegativeMoneySpec extends Specification {

    def "NegativeMoney can not be created when input is not a negative number"() {
        when:
        new NegativeMoney(input, Currency.CZK)

        then:
        def e = thrown(expectedException)
        e.message == exceptionMessage

        where:
        input || expectedException | exceptionMessage
        new BigDecimal("0.01") || IllegalArgumentException | "amount=0.01 must be a negative number"
        new BigDecimal("1") || IllegalArgumentException | "amount=1 must be a negative number"
        BigDecimal.ZERO || IllegalArgumentException | "amount=0 must be a negative number"
    }

    def "NegativeMoney can be created when input is a negative number"() {
        when:
        new NegativeMoney(new BigDecimal("-0.01"), Currency.CZK)

        then:
        notThrown()
    }
}
