package vacancy.model.response;

import lombok.Data;

/**
 * Created by felix on 28.04.15.
 */
@Data
public class UploadResponse extends Response {
    private DocumentHeader document;

    public static final Builder builder() {
        return new Builder();
    }

    public static final class Builder extends Response.Builder<UploadResponse, Builder> {

        @Override
        protected UploadResponse createModel() {
            return new UploadResponse();
        }

        public Builder document(DocumentHeader document) {
            model.setDocument(document);
            return this;
        }
    }
}
