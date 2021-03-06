package com.vesa.testprojmain.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@JsonPropertyOrder({
        "name",
        "seasons"
})
@JsonDeserialize(builder = Plant.Builder.class)
@Builder(builderClassName = "Builder", toBuilder = true)
@Getter
@AllArgsConstructor
public class Plant {

    @JsonProperty("name")
    private String name;

    @JsonProperty("seasons")
    private List<Season> seasons;

}
