package com.vesa.testprojmain.controller;

import com.vesa.testprojmain.domain.FunctionExecutionStatus;
import com.vesa.testprojmain.procedure.register.plant.RegisterPlantProcedure;
import com.vesa.testprojmain.procedure.register.plant.RegisterPlantRequest;
import com.vesa.testprojmain.procedure.register.plant.RegisterPlantResponse;
import com.vesa.testprojmain.procedure.register.plant.batch.RegisterPlantBatchProcedure;
import com.vesa.testprojmain.procedure.register.plant.batch.RegisterPlantBatchRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class RegisterPlantController {

    @Value("${application.plant.register.batch.enabled:false}")
    private boolean isBatchEnabled;

    @PostMapping("/register/plant")
    public RegisterPlantResponse registerPlant(@RequestBody final RegisterPlantRequest request) {
        final RegisterPlantProcedure procedure = new RegisterPlantProcedure(request);
        procedure.executeProcedure();
        return procedure.getProcResponse();
    }

    @PostMapping("/register/plant/batch")
    public RegisterPlantResponse registerPlants(@RequestBody final RegisterPlantBatchRequest request) {
        if (isBatchEnabled) {
            final RegisterPlantBatchProcedure procedure = new RegisterPlantBatchProcedure(request);
            procedure.executeProcedure();
            return procedure.getProcResponse();
        }
        return RegisterPlantResponse.builder()
                .functionExecutionStatus(FunctionExecutionStatus.builder()
                        .status(FunctionExecutionStatus.Status.FAILED)
                        .build())
                .errorDetails(Collections.singletonList("Endpoint for batch plant registration is not enabled"))
                .build();
    }

}