package vacancy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by felix on 29.04.15.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DocumentNotFoundException extends RuntimeException {
    public DocumentNotFoundException() {
    }

    public DocumentNotFoundException(String message) {
        super(message);
    }
}
