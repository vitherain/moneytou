package io.herain.moneytou.common.shared.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import org.springframework.boot.jackson.JsonComponent
import org.springframework.data.domain.PageRequest

@JsonComponent
class PageRequestDeserializer: JsonDeserializer<PageRequest>() {

    override fun deserialize(jsonParser: JsonParser, deserializationContext: DeserializationContext): PageRequest {
        val objectCodec: ObjectCodec = jsonParser.codec
        val node: JsonNode = objectCodec.readTree(jsonParser)

        val page = node.get("page").asInt()
        val size = node.get("size").asInt()
        return PageRequest.of(page, size)
    }
}
