package vacancy.model.response;

import lombok.Getter;
import lombok.Setter;
import vacancy.model.Status;

/**
 * Created by felix on 28.04.15.
 */
@Getter
@Setter
public abstract class Response {
    private Status status;
    private String error;

    public static abstract class Builder<M extends Response, B extends Builder> {

        protected M model = createModel();

        protected Builder() {
        }

        protected abstract M createModel();

        public B error(String error) {
            model.setError(error);
            return (B)this;
        }

        public B status(Status status) {
            model.setStatus(status);
            return (B)this;
        }

        public B ok() {
            return status(Status.OK);
        }

        public B fail() {
            return status(Status.FAIL);
        }

        public B fail(String error) {
            return (B)status(Status.FAIL).error(error);
        }

        public M build() {
            return model;
        }
    }
}
