package com.vesa.testprojmain.procedure.register.plant.batch;

import com.vesa.testprojmain.BaseProcedure;
import com.vesa.testprojmain.domain.FunctionExecutionStatus;
import com.vesa.testprojmain.domain.FunctionExecutionStatus.Status;
import com.vesa.testprojmain.domain.Plant;
import com.vesa.testprojmain.procedure.register.plant.RegisterPlantResponse;

public class RegisterPlantBatchProcedure extends BaseProcedure<RegisterPlantBatchRequest, RegisterPlantResponse> {

    public RegisterPlantBatchProcedure(final RegisterPlantBatchRequest request) {
        super(request);
        procRequest = request;
    }

    @Override
    public void execute() {
        procRequest.getPlants().forEach(plant -> {
            try {
                appServices.getPlantService().registerPlant(Plant.builder()
                        .name(plant.getName())
                        .seasons(plant.getSeasons())
                        .build());
            } catch (Exception e) {
                errorDetails.add(plant.getName());
            }
        });

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
