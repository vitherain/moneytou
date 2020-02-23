package io.herain.moneytou.graphql.scalars

import graphql.schema.Coercing
import io.herain.moneytou.domain.Currency

class CurrencyCoercing: Coercing<Currency, String> {

    override fun parseValue(input: Any?): Currency {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun parseLiteral(input: Any?): Currency {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun serialize(dataFetcherResult: Any?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
