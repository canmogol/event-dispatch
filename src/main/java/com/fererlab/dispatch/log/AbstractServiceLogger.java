package com.fererlab.dispatch.log;

import java.util.logging.Logger;

public class AbstractServiceLogger {

    private final String serviceName;
    private Logger L = Logger.getLogger(getClass().getSimpleName());

    public AbstractServiceLogger(String serviceName) {
        this.serviceName = serviceName;
    }

    private void log(String log) {
        L.info("[" + Thread.currentThread().getId() + "] [" + serviceName + "] " + log);
    }

    private void error(String log) {
        L.severe("[" + Thread.currentThread().getId() + "] [" + serviceName + "] " + log);
    }

    public void genericEventFired(String baseEvent) {
        log("Generic Event, will do nothing, baseEvent: " + baseEvent);
    }

    public void couldNotAddEventToQueue(String baseEvent, String errorMessage) {
        error("Could not add event to queue, event: " + baseEvent + " exception: " + errorMessage);
    }

    public void couldNotAccessMethod(String errorMessage) {
        error("Could not access/invoke method, exception: " + errorMessage);
    }

    public void serviceInterrupted(String errorMessage) {
        error("service interrupted, exception: " + errorMessage);
    }

    public void serviceIsShutdown() {
        log("shutdown");
    }

    public void shutdownServiceEvent(String event) {
        log("Shutdown Event, will shutdown this service, event: " + event);
    }
}
