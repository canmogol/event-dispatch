package com.fererlab.dispatch.log;

import java.util.logging.Logger;

public class AbstractEndpointLogger extends BaseLogger{

    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    public void noObserverFoundForEvent(String event) {
        error("there is no observer for this event: " + event);
    }

    public void endPointAlreadyRegistered(String event) {
        log("end point already registered to event: " + event);
    }

}
