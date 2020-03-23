package io.herain.moneytou.common.shared.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import org.springframework.boot.jackson.JsonComponent
import org.springframework.data.domain.PageRequest

@JsonComponent
class PageRequestDeserializer: JsonDeserializer<PageRequest>() {

    override fun deserialize(jsonParser: JsonParser, deserializationContext: DeserializationContext): PageRequest {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
