package com.cegames;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class MethodLogger extends TestWatcher
{
    public static String DEFAULT_PREFIX = "Starting test";
    public static String DEFAULT_FORMAT = "{} [{}]";
    
    private String messagePrefix = DEFAULT_PREFIX;
    private String messageFormat = DEFAULT_FORMAT;
    private Logger log = null;


    public MethodLogger() {
    }
    
    MethodLogger(Logger log) {
        this.log = log;
    }

    MethodLogger(Logger log, String messagePrefix) {
        this.log = log;
        this.messagePrefix = messagePrefix;
    }

    MethodLogger(Logger log, String messagePrefix, String messageFormat) {
        this.log = log;
        this.messagePrefix = messagePrefix;
        this.messageFormat = messageFormat;
    }

    /**
     * Whenever a test starts, this method logs the name of the test.
     * @param description
     */
    @Override
    protected void starting(Description description) {
        prepareLogger(description);
        log.info(messageFormat, messagePrefix, description.getMethodName());
    }

    /**
     * If logger is not initialized, initialize it.
     * @param description
     */
    private void prepareLogger(Description description) {
        if (log == null) {
            log = LoggerFactory.getLogger(description.getClassName());
        }
    }
    
}
