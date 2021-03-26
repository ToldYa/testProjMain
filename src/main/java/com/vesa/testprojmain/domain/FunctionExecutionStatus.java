package com.vesa.testprojmain.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status"
})
@Data
@Builder(toBuilder = true, builderClassName = "Builder")
public class FunctionExecutionStatus {

    @JsonProperty("status")
    private Status status;

    public enum Status {
        EXECUTED_SUCCESS("Executed-Success"),
        FAILED("Failed");

        private static final Map<String, Status> CONSTANTS = new HashMap<>();

        static {
            for (Status s : values()) {
                CONSTANTS.put(s.value, s);
            }
        }

        private final String value;

        Status(final String value) {
            this.value = value;
        }

        @JsonCreator
        public static Status fromValue(String value) {
            Status constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }
    }
}
