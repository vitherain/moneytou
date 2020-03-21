package io.herain.moneytou.tx.transaction.domain


import spock.lang.Specification

class MoneySpec extends Specification {

    def "Money can not be created when input is zero"() {
        when:
        new Money(BigDecimal.ZERO, Currency.CZK)

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "amount can not be zero"
    }

    def "Money can be created when input is a negative number"() {
        when:
        new Money(new BigDecimal("-0.01"), Currency.CZK)

        then:
        notThrown()
    }

    def "Money can be created when input is a positive number"() {
        when:
        new Money(new BigDecimal("0.01"), Currency.CZK)

        then:
        notThrown()
    }
}
