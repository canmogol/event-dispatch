package com.fererlab.dispatch.service;

import com.fererlab.dispatch.log.EventDispatcherLogger;

public class LoggerEventDispatcherListener implements EventDispatcherListener {

    private EventDispatcherLogger logger = new EventDispatcherLogger();

    @Override
    public void onCreate() {
        logger.eventDispatcherCreated();
    }

    @Override
    public void onServiceRegister(String serviceNames) {
        logger.willRegisterServices(serviceNames);
    }

    @Override
    public void onServiceStart(String serviceName) {
        logger.serviceStarted(serviceName);
    }

    @Override
    public void onCreationError(String serviceName, String errorMessage) {
        logger.couldNotCreateService(serviceName, errorMessage);
    }

    @Override
    public void onNotificationError(String serviceName, String errorMessage) {
        logger.couldNotNotifyService(serviceName, errorMessage);
    }

    @Override
    public void onServiceDiscoveryError(String errorMessage) {
        logger.couldNotDiscoverServices(errorMessage);
    }

    @Override
    public void noEventHandlerFound(String event) {
        logger.noEventHandler(event);
    }

    @Override
    public void onServiceShutdown() {
        logger.serviceShutdown();
    }

}
