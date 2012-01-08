package com.cegames;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Hello world!
 *
 */
public class MethodLogger extends TestWatcher
{

    private Logger log = null;

    public MethodLogger() {
    }
    
    MethodLogger(Logger logger) {
        log = logger;
    }

    @Override
    protected void starting(Description description) {
        prepareLogger(description);
        log.info("Starting [{}]", description.getMethodName());
    }

    /**
     * If logger is not initialized, initialize it.
     * @param description
     */
    private void prepareLogger(Description description) {
        if (log == null) {
            log = (Logger) LoggerFactory.getLogger(description.getClassName());
        }
    }
}
