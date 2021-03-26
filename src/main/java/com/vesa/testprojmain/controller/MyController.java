package com.vesa.testprojmain.controller;

import com.vesa.testprojmain.procedure.register.plant.RegisterPlantProcedure;
import com.vesa.testprojmain.procedure.register.plant.RegisterPlantProcedureBatch;
import com.vesa.testprojmain.procedure.register.plant.RegisterPlantRequest;
import com.vesa.testprojmain.procedure.register.plant.RegisterPlantResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping("/register/plant")
    public RegisterPlantResponse registerPlant(@RequestBody final RegisterPlantRequest request) {
        final RegisterPlantProcedure procedure = new RegisterPlantProcedure(request);
        procedure.executeProcedure();
        return procedure.getProcResponse();
    }

    @PostMapping("/register/plant/batch")
    public RegisterPlantResponse registerPlants(@RequestBody final List<RegisterPlantRequest> request) {
        final RegisterPlantProcedureBatch procedure = new RegisterPlantProcedureBatch(request);
        procedure.executeProcedure();
        return procedure.getProcResponse();
    }

}