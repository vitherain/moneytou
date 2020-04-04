package io.herain.moneytou.app.config

import io.herain.moneytou.shared.jpa.support.DefaultTransactionExecutor
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
@EntityScan("io.herain.moneytou.**.domain", "io.herain.moneytou.**.jpa")
class JpaConfiguration(private val platformTransactionManager: PlatformTransactionManager) {

    @Bean
    fun defaultTransactionExecutor(): DefaultTransactionExecutor {
        return DefaultTransactionExecutor(platformTransactionManager)
    }
}
