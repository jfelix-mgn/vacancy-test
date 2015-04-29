package vacancy.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by felix on 28.04.15.
 */
@Converter
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        if (attribute == null)
            return null;
        return Timestamp.from(attribute.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        if (dbData == null)
            return null;
        return dbData.toLocalDateTime();
    }
}
