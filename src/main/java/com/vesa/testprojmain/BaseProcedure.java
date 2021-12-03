package com.vesa.testprojmain;

import com.vesa.testprojmain.service.PlantService;

import java.util.Arrays;
import java.util.List;

public class BaseProcedure<T, P> extends AbstractProcedure<T, P> {

    protected static AppServices appServices;
    protected static PlantService plantService;
    protected List<String> customerIds;

    public BaseProcedure(final T request, String... customerIds) {
        super(request);
        this.customerIds = Arrays.asList(customerIds);
    }

    public synchronized static void setAppServices(final AppServices appServicesInstance) {
        appServices = appServicesInstance;
        plantService = appServices.getPlantService();
    }

}
