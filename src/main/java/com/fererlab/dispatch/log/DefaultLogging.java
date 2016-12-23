package com.fererlab.dispatch.log;

import java.util.Optional;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Stream;

public class DefaultLogging implements Logging {

    /**
     * log owner class
     */
    private final Class<?> owner;

    /**
     * format logger
     */
    private SimpleFormatter simpleFormatter;

    /**
     * util logger
     */
    private Logger logger;

    public DefaultLogging(Class<?> owner) {
	this(owner, new DispatcherLogFormatter());
    }

    public DefaultLogging(Class<?> owner, SimpleFormatter simpleFormatter) {
	this.owner = owner;
	this.simpleFormatter = simpleFormatter;
	logger = getLogger(getClass().getSimpleName());
    }

    private Logger getLogger(String name) {
	Logger logger = Logger.getLogger(name);
	// do not use parent handlers
	logger.setUseParentHandlers(false);

	Optional<Handler> handlerOptional = Stream.of(logger.getHandlers()).filter(h -> h instanceof DefaultConsoleHandler).findAny();
	if (!handlerOptional.isPresent()) {
	    // add new console handler
	    ConsoleHandler consoleHandler = new DefaultConsoleHandler();
	    consoleHandler.setFormatter(this.simpleFormatter);
	    logger.addHandler(consoleHandler);
	}
	return logger;
    }

    /**
     * java logger's info call with class name, method name and thread number
     *
     * @param log string log
     */
    @Override
    public void log(String log) {
	logger.info("[" + Thread.currentThread().getId() + "] [" + getClassNameMethodLineNumber() + "] " + log);
    }

    /**
     * java logger's severe call with class name, method name and thread number
     *
     * @param log string log
     */
    @Override
    public void error(String log) {
	logger.severe("[" + Thread.currentThread().getId() + "] [" + getClassNameMethodLineNumber() + "] " + log);
    }

    /**
     * gets the name of the caller method
     *
     * @return String method name
     */
    private String getClassNameMethodLineNumber() {
	// current call stack
	StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

	// find the owner class' stack
	StackTraceElement stackTraceElement;
	Optional<StackTraceElement> stackTraceElementOptional = Stream.of(stackTrace).filter(ste -> ste.getClassName().equals(this.owner.getName())).findFirst();
	if (stackTraceElementOptional.isPresent()) {
	    // owner has the logger instance
	    stackTraceElement = stackTraceElementOptional.get();
	} else {
	    // logger in an extended class
	    stackTraceElement = (stackTrace.length > 3) ? stackTrace[4] : ((stackTrace.length > 2) ? stackTrace[3] : stackTrace[0]);
	}
	// class method and line number
	String className = stackTraceElement.getClassName().substring(stackTraceElement.getClassName().lastIndexOf('.') + 1);
	String methodName = stackTraceElement.getMethodName();
	int lineNumber = stackTraceElement.getLineNumber();

	// return in format Class#method:number
	return String.format("%1$s#%2$s:%3$d", className, methodName, lineNumber);
    }


}
