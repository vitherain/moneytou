package io.herain.moneytou.graphql.scalars

import io.herain.moneytou.domain.Currency
import spock.lang.Specification
import spock.lang.Unroll

class CurrencyCoercingSpec extends Specification {

    @Unroll
    def 'currency is serialized to String'() {
        given:
            def coercing = new CurrencyCoercing()
        when:
            def serializedValue = coercing.serialize(Currency.CZK)
        then:
            serializedValue == "EUR"
    }

    @Unroll
    def 'getName should return whatever name is given'() {
        given: 'create testable instance'
            Testable testable = new Testable(inputName)

        when:
            String result = testable.toString()

        then:
            result == expectedResult

        where:
            inputName || expectedResult
            "Serdar"  || "Serdar"
            "John"    || "John"
            "doe"     || "doe"
    }

    class Testable {
        final String str

        Testable(final String str) {
            this.str = str
        }

        @Override
        String toString() {
            return str
        }
    }
}
