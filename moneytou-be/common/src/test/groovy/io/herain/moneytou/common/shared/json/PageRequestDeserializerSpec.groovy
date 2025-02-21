package io.herain.moneytou.common.shared.json

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.data.domain.PageRequest
import spock.lang.Specification

@JsonTest
class PageRequestDeserializerSpec extends Specification {

    @Autowired
    ObjectMapper objectMapper

    def "PageRequest can be deserialized"() {
        given:
        def json = "{\"page\": 5, \"size\": 30}"

        when:
        def pageRequest = objectMapper.readValue(json, PageRequest.class)

        then:
        pageRequest.pageNumber == 5
        pageRequest.pageSize == 30
    }
}
