package com.vesa.testprojmain;

import com.vesa.testprojmain.service.PlantService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Getter
@Service
public class AppServices {

    private PlantService plantService;

    @Autowired
    public void setPlantService(final PlantService plantService) {
        this.plantService = plantService;
    }

    @PostConstruct
    public void setAppServices() {
        BaseProcedure.setAppServices(this);
    }
}
