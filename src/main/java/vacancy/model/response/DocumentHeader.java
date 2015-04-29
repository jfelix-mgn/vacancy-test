package vacancy.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import vacancy.entity.Document;

/**
 * Created by felix on 28.04.15.
 */
@Data
@NoArgsConstructor
public class DocumentHeader {
    private long   id;
    private String name;
    private String time;

    public DocumentHeader(Document document) {
        this.id = document.getId();
        this.name = "Документ-" + document.getId();
        this.time = document.getFormattedTime();
    }
}
