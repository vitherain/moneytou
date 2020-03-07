package io.herain.moneytou.app.config

import graphql.Mutation
import graphql.Query
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GraphqlConfiguration {

    @Bean
    fun query(): Query {
        return Query()
    }

    @Bean
    fun mutation(): Mutation {
        return Mutation()
    }
}
