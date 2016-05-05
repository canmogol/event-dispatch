package com.fererlab.dispatch.log;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public abstract class BaseLogger {

    private Logger logger = getLogger(getClass().getSimpleName());

    private Logger getLogger(String name) {
        Logger logger = Logger.getLogger(name);
        // do not use parent handlers
        logger.setUseParentHandlers(false);
        // if there are any, remove them
        for (Handler handler : logger.getHandlers()) {
            System.out.println("removing already registered handler: " + handler);
            logger.removeHandler(handler);
        }
        // add new console handler
        System.out.println("adding a ConsoleHandler with GamLogFormatter");
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new BaseLogFormatter());
        logger.addHandler(consoleHandler);
        return logger;
    }


    /**
     * java logger's info call with class name, method name and thread number
     *
     * @param log string log
     */
    public void log(String log) {
        logger.info("[" + Thread.currentThread().getId() + "] [" + getClass().getSimpleName() + "#" + getMethod() + "] " + log);
    }

    /**
     * java logger's severe call with class name, method name and thread number
     *
     * @param log string log
     */
    public void error(String log) {
        logger.severe("[" + Thread.currentThread().getId() + "] [" + getClass().getSimpleName() + "#" + getMethod() + "] " + log);
    }

    /**
     * gets the name of the caller method
     *
     * @return String method name
     */
    private String getMethod() {
        String method = "";
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length > 3) {
            method = stackTrace[3].getMethodName() + ":" + stackTrace[3].getLineNumber();
        }
        return method;
    }


}
