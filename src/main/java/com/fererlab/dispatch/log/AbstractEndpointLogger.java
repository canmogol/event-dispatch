package com.fererlab.dispatch.log;

public class AbstractEndpointLogger extends DefaultLogging {

    public AbstractEndpointLogger(Class<?> owner) {
        super(owner);
    }

    public void noObserverFoundForEvent(String event) {
        error("there is no observer for this event: " + event);
    }

    public void endPointAlreadyRegistered(String event) {
        log("end point already registered to event: " + event);
    }

}
