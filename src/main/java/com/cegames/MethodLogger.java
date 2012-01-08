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

    private String messagePrefix = "Starting test";
    private Logger log = null;


    public MethodLogger() {
    }
    
    MethodLogger(Logger logger) {
        log = logger;
    }
    
    MethodLogger(Logger logger, String prefix) {
        log = logger;
        messagePrefix = prefix;
    }

    /**
     * Whenever a test starts, this method logs the name of the test.
     * @param description
     */
    @Override
    protected void starting(Description description) {
        prepareLogger(description);
        log.info("{} [{}]", messagePrefix, description.getMethodName());
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
