package io.herain.moneytou.common.shared.time

import org.springframework.stereotype.Component
import java.time.OffsetDateTime
import java.util.function.Supplier

@Component
class OffsetDateTimeProvider: Supplier<OffsetDateTime> {
    override fun get(): OffsetDateTime {
        return OffsetDateTime.now()
    }
}
