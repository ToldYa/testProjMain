package com.vesa.testprojmain.procedure.register.plant;

import com.vesa.testprojmain.BaseProcedure;
import com.vesa.testprojmain.domain.FunctionExecutionStatus;
import com.vesa.testprojmain.domain.FunctionExecutionStatus.Status;
import com.vesa.testprojmain.domain.Plant;
import com.vesa.testprojmain.exception.BadRequestException;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import com.vesa.testprojmain.util.StrUtil;

@Log4j2
public final class RegisterPlantProcedure extends BaseProcedure<RegisterPlantRequest, RegisterPlantResponse> {

    public RegisterPlantProcedure(final String customerId, final RegisterPlantRequest request) {
        super(request, customerId);
    }

    @Override
    public void execute() {
        try {
            plantService.registerPlant(Plant.builder()
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

    @SneakyThrows
    @Override
    public void validateRequest() {
        if (StrUtil.nullOrEmpty(procRequest.getPlant().getName())) {
            throw new BadRequestException("Plant name must be provided in request");
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
