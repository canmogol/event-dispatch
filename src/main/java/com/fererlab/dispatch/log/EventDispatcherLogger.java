package com.fererlab.dispatch.log;

public class EventDispatcherLogger extends BaseLogger {

    public void couldNotDiscoverServices(String message) {
        log("Could not discover services, exception message: " + message);
    }

    public void couldNotCreateService(String serviceName, String errorMessage) {
        error("Could not create service: " + serviceName + " exception: " + errorMessage);
    }

    public void couldNotNotifyService(String serviceName, String errorMessage) {
        error("Could not notify service: " + serviceName + " exception: " + errorMessage);
    }

    public void noEventHandler(String event) {
        error("!!! THERE IS NO REGISTERED SERVICE FOR THIS EVENT: " + event);
    }

}
