package io.herain.moneytou.common.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.UUID
import java.util.function.Supplier

@Configuration
class SecurityConfiguration {

    @Bean
    fun currentUserIdSupplier(): Supplier<UUID> {
        return Supplier { UUID.fromString("e215bfe5-11cd-482c-9fe4-84637b744cd0") }
    }
}
