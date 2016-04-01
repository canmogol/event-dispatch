package com.fererlab.dispatch.endpoint;

import com.fererlab.dispatch.event.Event;

public interface EndpointObserver<T extends Event> {

    void handleEvent(T event);

}
