package com.fererlab.dispatch.event;

public class ShutdownImmediatelyEvent extends BaseEvent {

    private ShutdownEvent shutdownEvent;

    public ShutdownImmediatelyEvent(ShutdownEvent shutdownEvent) {
        this.shutdownEvent = shutdownEvent;
    }

    public ShutdownEvent getShutdownEvent() {
        return shutdownEvent;
    }

}
