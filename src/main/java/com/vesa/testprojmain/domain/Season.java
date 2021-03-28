package com.vesa.testprojmain.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum Season {
    SPRING("Spring"),
    SUMMER("Summer"),
    FALL("Fall"),
    WINTER("Winter");

    private static final Map<String, Season> CONSTANTS = new HashMap<>();

    static {
        for (Season s : values()) {
            CONSTANTS.put(s.value, s);
        }
    }

    private final String value;

    Season(final String value) {
        this.value = value;
    }

    @JsonCreator
    public static Season fromValue(final String value) {
        Season constant = CONSTANTS.get(value);
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
