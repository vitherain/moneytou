package io.herain.moneytou.graphql.scalars

import graphql.schema.CoercingSerializeException
import io.herain.moneytou.domain.Currency
import spock.lang.Specification

class CurrencyCoercingSpec extends Specification {

    def 'currency is serialized to String'() {
        given:
        CurrencyCoercing coercing = new CurrencyCoercing()
        when:
        String result = coercing.serialize(inputCurrency)
        then:
        result == expectedResult
        where:
        inputCurrency || expectedResult
        Currency.CZK  || "CZK"
        Currency.EUR  || "EUR"
        Currency.USD  || "USD"
    }

    def 'currency serialization ends with CoercingSerializeException when input is not Currency'() {
        given:
        CurrencyCoercing coercing = new CurrencyCoercing()
        when:
        coercing.serialize(input)
        then:
        thrown(CoercingSerializeException)
        where:
        input << [new Object(), "CZK"]
    }
}
