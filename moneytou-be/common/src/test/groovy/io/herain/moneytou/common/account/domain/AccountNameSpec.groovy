package io.herain.moneytou.common.account.domain

import io.herain.moneytou.common.account.domain.Account
import spock.lang.Specification

class AccountNameSpec extends Specification {

    def "AccountName can not be created when input is too long"() {
        when:
        new Account.AccountName("this text is so long that it is too much for an account name")

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "name='this text is so long that it is too much for an account name' must be 40 characters long at most"
    }

    def "AccountName can be created when input is ok"() {
        when:
        new Account.AccountName("this text is ok")

        then:
        notThrown()
    }
}
