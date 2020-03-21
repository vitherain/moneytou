package io.herain.moneytou.tx.domain

import spock.lang.Specification

class LabelSpec extends Specification {

    def "Label can not be created when input is too long"() {
        when:
        new Label("this text is so long that it is too much for an label name")

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "name='this text is so long that it is too much for an label name' must be 40 characters long at most"
    }

    def "Label can be created when input is ok"() {
        when:
        new Label("this text is ok")

        then:
        notThrown()
    }
}
