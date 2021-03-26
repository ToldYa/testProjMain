package com.vesa.testprojmain.procedure.register.plant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vesa.testprojmain.domain.FunctionExecutionStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "functionExecutionStatus",
        "errorDetails"
})
@Builder
@Data
public class RegisterPlantResponse {

    @JsonProperty("functionExecutionStatus")
    final FunctionExecutionStatus functionExecutionStatus;

    @JsonProperty("errorDetails")
    final List<String> errorDetails;

}
