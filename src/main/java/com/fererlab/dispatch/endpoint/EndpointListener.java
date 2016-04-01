package com.fererlab.dispatch.endpoint;

import com.fererlab.dispatch.event.Event;
import com.fererlab.dispatch.service.Service;

import java.io.Closeable;

public interface EndpointListener extends Closeable, Runnable {

    String getStatus();

    void handleEvent(Event event);

    void broadcast(Event baseEvent);

    void broadcast(Event baseEvent, Class<? extends Event> eventClass, EndpointObserver<Event> endpointObserver);

    Service getService();

}
