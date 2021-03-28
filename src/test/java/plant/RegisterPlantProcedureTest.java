package plant;

import com.vesa.testprojmain.domain.FunctionExecutionStatus.Status;
import com.vesa.testprojmain.domain.Plant;
import com.vesa.testprojmain.procedure.register.plant.RegisterPlantProcedure;
import com.vesa.testprojmain.procedure.register.plant.RegisterPlantRequest;
import com.vesa.testprojmain.procedure.register.plant.RegisterPlantResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RegisterPlantProcedureTest extends PlantTestBase {

    private RegisterPlantRequest request;
    private RegisterPlantResponse response;

    private RegisterPlantProcedure procedure;

    @Test
    public void successfulRegisterPlant() {
        build();
        verifySuccessful();
    }

    private void verifySuccessful() {
        assertEquals(Status.EXECUTED_SUCCESS, response.getFunctionExecutionStatus().getStatus(),
                String.format("RegisterPlantResponse should report status [%s]", Status.EXECUTED_SUCCESS.toString()));
        assertNull(response.getErrorDetails(), "Error details in response should not be present");
    }

    private void build() {
        request = RegisterPlantRequest.builder()
                .plant(Plant.builder()
                        .name(DEFAULT_PLANT_NAME)
                        .seasons(DEFAULT_SEASONS)
                        .build())
                .build();

        procedure = new RegisterPlantProcedure(request);
        procedure.executeProcedure();

        response = procedure.getProcResponse();
    }

}
