package io.herain.moneytou.common.category.domain

import spock.lang.Specification
import spock.lang.Unroll

class CategoryCodeSpec extends Specification {

    def "CategoryCode can be created when input is ok"() {
        when:
        new Category.CategoryCode("SOME_CODE")

        then:
        notThrown()
    }

    def "CategoryCode can not be created when input is too long"() {
        given:
        String code = "CODE_S_NEKRESTANSKY_DLOUHYM_NAZVEM_TAKOVEJ_NAZEV_SE_VUBEC_NEHODI_NA_NAZEV_KATEGORIE_" +
                "CODE_S_NEKRESTANSKY_DLOUHYM_NAZVEM_TAKOVEJ_NAZEV_SE_VUBEC_NEHODI_NA_NAZEV_KATEGORIE_" +
                "CODE_S_NEKRESTANSKY_DLOUHYM_NAZVEM_TAKOVEJ_NAZEV_SE_VUBEC_NEHODI_NA_NAZEV_KATEGORIEEEEEE"
        when:
        new Category.CategoryCode(code)

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "code='${code}' is not valid"
    }

    @Unroll("CategoryCode can not be created when is not valid: '#input'")
    def "CategoryCode can not be created when is not valid"() {
        when:
        new Category.CategoryCode(input)

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "code='${input}' is not valid"

        where:
        input | _
        "A-CODE" | _
        "3_CODE" | _
        "_CODE" | _
        "-CODE" | _
        "A,CODE" | _
        "A;CODE" | _
        ":CODE" | _
        ";CODE" | _
        "A<CODE" | _
        "A>CODE" | _
        "A!CODE" | _
        "?CODE" | _
        "{CODE" | _
        "CÓ_DE" | _
        "SOME_cODE" | _
        "ŽŠČŘĎŤŇ" | _
    }
}
