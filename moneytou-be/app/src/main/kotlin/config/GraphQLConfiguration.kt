package io.herain.moneytou.app.config

import io.herain.moneytou.app.graphql.Query
import io.herain.moneytou.app.graphql.type.Mutation
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
