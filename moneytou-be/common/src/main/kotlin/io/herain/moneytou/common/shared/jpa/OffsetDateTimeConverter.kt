package io.herain.moneytou.common.shared.jpa

import java.sql.Timestamp
import java.time.OffsetDateTime
import java.time.ZoneOffset
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class OffsetDateTimeConverter : AttributeConverter<OffsetDateTime, Timestamp> {

    override fun convertToDatabaseColumn(attribute: OffsetDateTime?): Timestamp? {
        return if (attribute == null)
            null
        else
            Timestamp.valueOf(attribute.withOffsetSameInstant(ZoneOffset.UTC).toLocalDateTime())
    }

    override fun convertToEntityAttribute(dbData: Timestamp?): OffsetDateTime? {
        return dbData?.toLocalDateTime()?.atOffset(ZoneOffset.UTC)
    }
}
