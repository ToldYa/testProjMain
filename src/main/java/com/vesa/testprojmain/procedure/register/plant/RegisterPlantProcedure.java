package com.vesa.testprojmain.procedure.register.plant;

import com.vesa.testprojmain.BaseProcedure;
import com.vesa.testprojmain.domain.FunctionExecutionStatus;
import com.vesa.testprojmain.domain.FunctionExecutionStatus.Status;
import com.vesa.testprojmain.domain.Plant;

public class RegisterPlantProcedure extends BaseProcedure<RegisterPlantRequest, RegisterPlantResponse> {

    public RegisterPlantProcedure(final RegisterPlantRequest request) {
        super(request);
        procRequest = request;
    }

    @Override
    public void execute() {
        try {
            appServices.getPlantService().registerPlant(Plant.builder()
                    .name(procRequest.getPlant().getName())
                    .seasons(procRequest.getPlant().getSeasons())
                    .build());
        } catch (Exception e) {
            errorDetails.add(procRequest.getPlant().getName());
        }

        if (errorDetails.isEmpty()) {
            buildSuccessfulResponse();
        } else {
            this.procResponse = RegisterPlantResponse.builder()
                    .functionExecutionStatus(FunctionExecutionStatus.builder()
                            .status(Status.FAILED)
                            .build())
                    .errorDetails(errorDetails)
                    .build();
        }

    }

    protected void buildSuccessfulResponse() {
        this.procResponse = RegisterPlantResponse.builder()
                .functionExecutionStatus(FunctionExecutionStatus.builder()
                        .status(Status.EXECUTED_SUCCESS)
                        .build())
                .build();
    }

}
