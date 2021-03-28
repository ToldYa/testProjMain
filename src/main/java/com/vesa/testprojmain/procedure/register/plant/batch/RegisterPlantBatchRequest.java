package com.vesa.testprojmain.procedure.register.plant.batch;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vesa.testprojmain.domain.Plant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@JsonDeserialize(builder = RegisterPlantBatchRequest.Builder.class)
@JsonPropertyOrder({
        "plants"
})
@Builder(builderClassName = "Builder", toBuilder = true)
@Getter
@AllArgsConstructor
public class RegisterPlantBatchRequest {

    @NonNull
    @JsonProperty("plants")
    private List<Plant> plants;

}
