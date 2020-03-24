package io.herain.moneytou.common.shared.json

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.junit.jupiter.SpringExtension
import spock.lang.Specification

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PageRequestDeserializerTest extends Specification {

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
