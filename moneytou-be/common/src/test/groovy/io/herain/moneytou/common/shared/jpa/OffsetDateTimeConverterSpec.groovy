package io.herain.moneytou.common.shared.jpa

import spock.lang.Specification

import java.sql.Timestamp
import java.time.OffsetDateTime

class OffsetDateTimeConverterSpec extends Specification {

    OffsetDateTimeConverter converter = new OffsetDateTimeConverter()

    def "null is converted to database column value as null"() {
        when:
        Timestamp timestamp = converter.convertToDatabaseColumn(null)

        then:
        timestamp == null
    }

    def "date with UTC time zone is converted to database column value"() {
        given:
        OffsetDateTime attribute = OffsetDateTime.parse("2020-04-03T20:40:41Z")

        when:
        Timestamp timestamp = converter.convertToDatabaseColumn(attribute)

        then:
        timestamp == new Timestamp(120, 3, 3, 20, 40, 41, 0)
    }

    def "date with non-UTC time zone is converted to database column value"() {
        given:
        OffsetDateTime attribute = OffsetDateTime.parse("2020-04-03T22:40:41+02:00")

        when:
        Timestamp timestamp = converter.convertToDatabaseColumn(attribute)

        then:
        timestamp == new Timestamp(120, 3, 3, 20, 40, 41, 0)
    }

    def "null is converted to entity attribute value as null"() {
        when:
        OffsetDateTime time = converter.convertToEntityAttribute(null)

        then:
        time == null
    }

    def "date with UTC time zone is converted to entity attribute value"() {
        given:
        Timestamp timestamp = new Timestamp(120, 3, 3, 20, 40, 41, 0)

        when:
        OffsetDateTime time = converter.convertToEntityAttribute(timestamp)

        then:
        time == OffsetDateTime.parse("2020-04-03T20:40:41Z")
    }
}
