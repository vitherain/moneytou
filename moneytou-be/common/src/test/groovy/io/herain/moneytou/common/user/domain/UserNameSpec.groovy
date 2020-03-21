package io.herain.moneytou.common.user.domain

import spock.lang.Specification

class UserNameSpec extends Specification {

    def "UserName can not be created when input is too long"() {
        when:
        new User.UserName("this_text_is_so_long_that_it_is_too_much_for_an_category_name_this_text_is_so_long_that_it_is_too_muc")

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "username='this_text_is_so_long_that_it_is_too_much_for_an_category_name_this_text_is_so_long_that_it_is_too_muc' must be 100 characters long at most"
    }

    def "UserName can not contain whitespaces"() {
        when:
        new User.UserName(input)

        then:
        def e = thrown(IllegalArgumentException)
        e.message == expectedMessage

        where:
        input || expectedMessage
        " ok " || "username=' ok ' must not contain any whitespace"
        "ok ok" || "username='ok ok' must not contain any whitespace"
        " " || "username=' ' must not contain any whitespace"
    }

    def "UserName can not be empty"() {
        when:
        new User.UserName("")

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "username must not be empty"
    }

    def "UserName can be created when input is ok"() {
        when:
        new User.UserName("this_username_is_ok_this_username_is_ok_this_username_is_ok_this_username_is_ok_this_username_is_ok")

        then:
        notThrown()
    }
}
