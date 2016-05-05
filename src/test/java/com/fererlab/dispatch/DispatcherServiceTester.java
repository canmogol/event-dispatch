package com.fererlab.dispatch;

import com.fererlab.dispatch.event.ShutdownEvent;
import com.fererlab.dispatch.service.EventDispatcher;
import com.fererlab.dispatch.service.EventDispatcherListener;
import com.fererlab.dispatch.util.Configuration;
import net.jodah.concurrentunit.Waiter;
import org.junit.Test;

import java.util.concurrent.TimeoutException;

public class DispatcherServiceTester {

    @Test
    public void testDispatcherStartStop() throws TimeoutException {
        // will wait for shutdown or until timeout
        final Waiter waiter = new Waiter();

        // create empty configuration
        Configuration configuration = new Configuration();
        // run dispatcher with this configuration
        EventDispatcher eventDispatcher = new EventDispatcher(configuration);
        eventDispatcher.addListener(new EventDispatcherListener() {
            @Override
            public void onCreate() {

            }

            @Override
            public void onServiceRegister(String serviceNames) {

            }

            @Override
            public void onServiceStart(String serviceName) {

            }

            @Override
            public void onCreationError(String serviceName, String errorMessage) {

            }

            @Override
            public void onNotificationError(String serviceName, String errorMessage) {

            }

            @Override
            public void onServiceDiscoveryError(String errorMessage) {

            }

            @Override
            public void noEventHandlerFound(String event) {

            }

            @Override
            public void onServiceShutdown() {
                waiter.resume();
            }
        });
        //eventDispatcher.addListener();
        new Thread(eventDispatcher).start();
        // Create Shutdown Event
        ShutdownEvent shutdownEvent = new ShutdownEvent();
        // shutdown event dispatcher
        eventDispatcher.notify(shutdownEvent);

        // wait until timeout
        waiter.await(3000);
    }

}
