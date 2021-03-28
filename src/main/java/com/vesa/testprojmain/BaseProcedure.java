package com.vesa.testprojmain;

public class BaseProcedure<T, P> extends AbstractProcedure<T, P> {

    protected static AppServices appServices;

    public BaseProcedure(final T request) {
        super(request);
    }

    public synchronized static void setAppServices(final AppServices appServicesInstance) {
        appServices = appServicesInstance;
    }

}
