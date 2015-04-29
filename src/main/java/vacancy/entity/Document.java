package vacancy.entity;

import lombok.Data;
import vacancy.Constants;
import vacancy.converter.LocalDateTimeConverter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by felix on 28.04.15.
 */
@Data
@XmlRootElement(name = "document")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Document {
    @XmlTransient
    @Id
    @GeneratedValue
    private Long          id;
    @XmlTransient
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime uploadTime;
    @XmlElement(name = "property")
    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Property> properties = new LinkedList<Property>();

    public String getFormattedTime() {
        return uploadTime.format(Constants.DATE_TIME_FORMATTER);
    }
}
