package io.herain.moneytou.graphql.scalars

import graphql.schema.GraphQLScalarType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CustomScalarsConfiguration {

    @Bean
    fun currencyScalar(): GraphQLScalarType {
        return GraphQLScalarType.newScalar()
                .name("Currency")
                .description("Currency scalar type")
                .coercing(CurrencyCoercing())
                .build()
    }
}
