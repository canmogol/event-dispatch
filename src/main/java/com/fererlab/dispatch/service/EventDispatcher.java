package com.fererlab.dispatch.service;

import com.fererlab.dispatch.event.*;
import com.fererlab.dispatch.util.Configuration;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EventDispatcher extends AbstractService {

    private List<EventDispatcherListener> listeners = new ArrayList<EventDispatcherListener>() {{
        add(new LoggerEventDispatcherListener());
    }};
    private Map<Class<? extends Event>, Set<Service>> eventServiceMap = new ConcurrentHashMap<>();
    private Set<Service> services = new HashSet<>();
    private Configuration configuration;

    public EventDispatcher(Configuration configuration) {
        super(new HashSet<Class<? extends Event>>() {{
            add(EventsRegisteredEvent.class);
            add(EventsUnregisteredEvent.class);
        }});
        this.configuration = configuration;
        listeners.stream().forEach(EventDispatcherListener::onCreate);
    }

    @Override
    public void run() {
        discoverServices();
        registerServices();
        for (Service service : services) {
            listeners.stream().forEach(listener -> listener.onServiceStart(service.getClass().getName()));
            new Thread(service).start();
        }
        super.run();
    }

    private void discoverServices() {
        // TODO discover services from command line parameters, classpath etc.
        // notify listener if there might be en error while discovering services
        // listeners.stream().forEach(listener -> listener.onServiceDiscoveryError(""));
    }

    private Set<Service> registerServices() {
        // collect service names for logging
        StringBuilder serviceNames = new StringBuilder().append("[");
        configuration.getServices().stream().forEach(aClass -> serviceNames.append(aClass.getName()).append(","));
        // call listener
        listeners.stream().forEach(listener -> listener.onServiceRegister(serviceNames.append("]").toString()));
        // initialize services and set properties and broadcast service
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
                listeners.stream().forEach(listener -> listener.onCreationError(sClass.toString(), e.getMessage()));
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
                    listeners.stream().forEach(listener -> listener.onNotificationError(service.toString(), pikachu.getMessage()));
                }
            }
        } else {
            listeners.stream().forEach(listener -> listener.noEventHandlerFound(event.toString()));
        }
    }

    public void addListener(EventDispatcherListener listener) {
        listeners.add(listener);
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
        // notify listeners
        listeners.stream().forEach(EventDispatcherListener::onServiceShutdown);
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
