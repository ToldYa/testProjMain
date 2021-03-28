package com.vesa.testprojmain.procedure.register.plant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vesa.testprojmain.domain.Plant;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@JsonDeserialize(builder = RegisterPlantRequest.Builder.class)
@JsonPropertyOrder({
        "plant"
})
@Getter
@Builder(builderClassName = "Builder", toBuilder = true)
public class RegisterPlantRequest {

    @NonNull
    @JsonProperty("plant")
    private Plant plant;

}
