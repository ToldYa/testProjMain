package com.vesa.testprojmain.procedure.register.plant;

import com.vesa.testprojmain.AbstractProcedure;
import com.vesa.testprojmain.domain.FunctionExecutionStatus;
import com.vesa.testprojmain.domain.FunctionExecutionStatus.Status;
import com.vesa.testprojmain.domain.Plant;

import java.util.List;

public class RegisterPlantProcedureBatch extends AbstractProcedure<List<RegisterPlantRequest>, RegisterPlantResponse> {

    public RegisterPlantProcedureBatch(final List<RegisterPlantRequest> request) {
        super(request);
        procRequest = request;
    }

    public void execute() {

        procRequest.forEach(request -> {
            try {
                plantService.registerPlant(Plant.builder()
                        .name(request.getPlant().getName())
                        .seasons(request.getPlant().getSeasons())
                        .build());
            } catch (Exception e) {
                errorDetails.add(request.getPlant().getName());
            }
        });

        if (errorDetails.isEmpty()) {
            buildSuccessfulResponse();
        } else {
            this.procResponse = RegisterPlantResponse.builder()
                    .functionExecutionStatus(FunctionExecutionStatus.builder()
                            .status(Status.EXECUTED_SUCCESS)
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
