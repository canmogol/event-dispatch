package com.fererlab.dispatch.log;

public class AbstractServiceLogger extends BaseLogger {

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

    public void serviceIsShutdown(String serviceName) {
        log(serviceName + " service shutdown");
    }

    public void shutdownServiceEvent(String serviceName, String event) {
        log("Shutdown Event, will shutdown " + serviceName + " service, event: " + event);
    }
}
