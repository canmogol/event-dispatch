package com.fererlab.dispatch.service;

public interface EventDispatcherListener {

    void onCreate();

    void onServiceRegister(String serviceNames);

    void onServiceStart(String serviceName);

    void onCreationError(String serviceName, String errorMessage);

    void onNotificationError(String serviceName, String errorMessage);

    void onServiceDiscoveryError(String errorMessage);

    void noEventHandlerFound(String event);

    void onServiceShutdown();

}
