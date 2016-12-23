package com.fererlab.dispatch.log;

public class EventDispatcherLogger extends DefaultLogging {

    public EventDispatcherLogger(Class<?> owner) {
        super(owner);
    }

    public void couldNotDiscoverServices(String errorMessage) {
        error("Could not create services, exception: " + errorMessage);
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

    public void eventDispatcherCreated() {
        log("Event dispatcher created");
    }

    public void willRegisterServices(String serviceNames) {
        log("will register these services: " + serviceNames);
    }

    public void serviceStarted(String serviceName) {
        log(serviceName + "service started");
    }

    public void serviceShutdown() {
        log("Shutdown event fired, will notify registered services");
    }
}
