package shares;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * response body returns data to the client, including timestamp, code, status, result
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBody {
    private Object result;
    private int status;
    private String message;
    private int code;
    private long timestamp = System.currentTimeMillis();


    public ResponseBody() {
    }

    public Object getResult() {
        return result;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public ResponseBody(Object result, Status status, String message, Code code) {
        this.result = result;
        this.status = status.value();
        this.message = message;
        this.code = code.value();
    }

    public ResponseBody(Object result, Status status, String message, int code) {
        this.result = result;
        this.status = status.value();
        this.message = message;
        this.code = code;
    }

    public ResponseBody(Object result, Status status, Code code) {
        this.result = result;
        this.status = status.value();
        this.message = code.message();
        this.code = code.value();
    }

    public ResponseBody(Object result, Status status, Code code, long timestamp) {
        this.result = result;
        this.status = status.value();
        this.message = code.message();
        this.code = code.value();
        this.timestamp = timestamp;
    }

    public enum Status {
        SUCCESS(1),
        FAILED(0);

        private final int value;

        Status(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    public enum Code {
        SUCCESS(200, "Successful"),
        UNAUTHORIZED_REQUEST(403, "Unauthorized request"),
        DATA_ERROR(406, "Data error"),
        NOT_FOUND(404, "Not found"),
        BAD_REQUEST(400, "Bad request"),
        INTERNAL_ERROR(500, "Internal server error");

        private final int value;
        private final String message;

        Code(int value, String message) {
            this.value = value;
            this.message = message;
        }

        public String message() {
            return this.message;
        }

        public int value() {
            return this.value;
        }

        public static Code fromValue(Integer value) {
            if(value != null) {
                for (Code code : values()) {
                    if (Objects.equals(code.value, value)) {
                        return code;
                    }
                }
                throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
            }
            else throw new IllegalArgumentException("Value cannot be null or empty!");

        }
    }
}
