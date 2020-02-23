package io.herain.moneytou.graphql.scalars

import graphql.schema.Coercing
import graphql.schema.CoercingSerializeException
import io.herain.moneytou.domain.Currency

class CurrencyCoercing: Coercing<Currency, String> {

    override fun parseValue(input: Any?): Currency {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun parseLiteral(input: Any?): Currency {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun serialize(dataFetcherResult: Any?): String {
        if (dataFetcherResult is Currency) {
            return dataFetcherResult.name;
        }
        throw CoercingSerializeException("dataFetcherResult is not of type ${Currency::class.qualifiedName}");
    }
}
