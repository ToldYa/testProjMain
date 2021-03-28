package com.vesa.testprojmain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class AbstractProcedure<T, P> {

    protected T procRequest;
    protected P procResponse;

    protected List<String> errorDetails = new ArrayList<>();

    public AbstractProcedure(T request) {
        this.procRequest = request;
    }

    public void execute() {
    }

    public void validateRequest() {
    }

    public void executeProcedure() {
        validateRequest();
        execute();
    }
}
