package com.fererlab.dispatch.service;

import com.fererlab.dispatch.event.*;
import com.fererlab.dispatch.log.EventDispatcherLogger;
import com.fererlab.dispatch.util.Configuration;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class EventDispatcher extends AbstractService {

    private EventDispatcherLogger logger = new EventDispatcherLogger();
    private Map<Class<? extends Event>, Set<Service>> eventServiceMap = new ConcurrentHashMap<>();
    private Set<Service> services = new HashSet<>();
    private Configuration configuration;

    public EventDispatcher(Configuration configuration) {
        super(new HashSet<Class<? extends Event>>() {{
            add(EventsRegisteredEvent.class);
            add(EventsUnregisteredEvent.class);
        }});
        this.configuration = configuration;
    }

    @Override
    public void run() {
        registerServices();
        for (Service service : services) {
            new Thread(service).start();
        }
        super.run();
    }

    private Set<Service> registerServices() {
        for (Class<? extends Service> sClass : configuration.getServices()) {
            try {
                Service service = sClass.newInstance();
                service.setBroadcastService(this);
                service.setProperties(configuration.getProperties());
                Set<Class<? extends Event>> eventsToListen = service.getEventsToListen();
                for (Class<? extends Event> eventClass : eventsToListen) {
                    if (!eventServiceMap.containsKey(eventClass)) {
                        Set<Service> serviceSet = new HashSet<>();
                        serviceSet.add(service);
                        eventServiceMap.put(eventClass, serviceSet);
                    } else {
                        eventServiceMap.get(eventClass).add(service);
                    }
                }
                services.add(service);
            } catch (InstantiationException | IllegalAccessException e) {
                logger.couldNotCreateService(sClass.toString(), e.getMessage());
            }
        }
        return services;
    }

    @Override
    public void handleEvent(Event event) {
        if (eventServiceMap.containsKey(event.getClass()) && eventServiceMap.get(event.getClass()).size() > 0) {
            for (Service service : eventServiceMap.get(event.getClass())) {
                try {
                    service.notify(event);
                } catch (Throwable pikachu) {
                    logger.couldNotNotifyService(service.toString(), pikachu.getMessage());
                }
            }
        } else {
            logger.noEventHandler(event.toString());
        }
    }

    public void handleEvent(ShutdownImmediatelyEvent event) {
        // clear all events for services registered to ShutdownEvent
        if (eventServiceMap.containsKey(ShutdownEvent.class)) {
            eventServiceMap.get(ShutdownEvent.class).forEach(Service::dropEvents);
        }
        // notify ShutdownEvent
        handleEvent(event.getShutdownEvent());
    }

    @Override
    public void handleEvent(ShutdownEvent event) {
        // first shutdown other services
        handleEvent((BaseEvent) event);
        // then shutdown event dispatcher
        super.handleEvent(event);
    }

    public void handleEvent(EventsRegisteredEvent event) {
        // add this service for each of the event types
        for (Class<? extends Event> eventClass : event.getEvents()) {
            if (!eventServiceMap.containsKey(eventClass)) {
                Set<Service> serviceSet = new HashSet<>();
                serviceSet.add(event.getService());
                eventServiceMap.put(eventClass, serviceSet);
            } else {
                eventServiceMap.get(eventClass).add(event.getService());
            }
        }
    }

    public void handleEvent(EventsUnregisteredEvent event) {
        // remove this service for each of the event types
        event.getEvents().forEach(eventClass -> {
            if (eventServiceMap.containsKey(eventClass)) {
                eventServiceMap.get(eventClass).remove(event.getService());
            }
        });
    }
}
