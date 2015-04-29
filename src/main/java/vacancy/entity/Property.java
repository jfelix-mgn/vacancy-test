package vacancy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by felix on 28.04.15.
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Property {
    @XmlTransient
    @Id
    @GeneratedValue
    private Long   id;
    @XmlAttribute
    private String name;
    @XmlElement(name = "property")
    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Property> properties = new LinkedList<Property>();

    public Property(String name) {
        this.name = name;
    }
}
