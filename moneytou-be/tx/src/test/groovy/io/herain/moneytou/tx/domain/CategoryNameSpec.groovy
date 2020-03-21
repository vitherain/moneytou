package io.herain.moneytou.tx.domain

import spock.lang.Specification

class CategoryNameSpec extends Specification {

    def "CategoryName can not be created when input is too long"() {
        when:
        new Category.CategoryName("this text is so long that it is too much for an category name")

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "name='this text is so long that it is too much for an category name' must be 40 characters long at most"
    }

    def "CategoryName can be created when input is ok"() {
        when:
        new Category.CategoryName("this text is ok")

        then:
        notThrown()
    }
}
