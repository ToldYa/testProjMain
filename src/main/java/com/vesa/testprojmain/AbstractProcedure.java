package com.vesa.testprojmain;

import com.vesa.testprojmain.service.PlantService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class AbstractProcedure<T, P> {

    protected T procRequest;
    protected P procResponse;

    protected List<String> errorDetails = new ArrayList<>();

    @Autowired
    protected PlantService plantService;

    public AbstractProcedure(T request) {
        this.procRequest = request;
    }

    public abstract void execute();
    public void validateRequest(){};

    public void executeProcedure() {
        validateRequest();
        execute();
    }
}
