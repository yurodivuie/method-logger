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
    public static String DEFAULT_FORMAT = "Starting test [{}]";
    
    private final String messageFormat;
    private Logger log = null;


    /**
     * Default constructor.  Will use the logger for the class under test,
     * and will display messages using the DEFAULT_FORMAT.  Logger is
     * obtained when the test method starts.
     */
    public MethodLogger() {
        this.messageFormat = DEFAULT_FORMAT;
    }

    /**
     * Constructor expects an slf4j Logger.  DEFAULT_FORMAT used for
     * messages.
     * 
     * @param log   slf4j logger
     */
    MethodLogger(Logger log) {
        this.messageFormat = DEFAULT_FORMAT;
        this.log = log;
    }

    /**
     * Constructor expects a logger and a format for how the message should
     * be displayed.
     *
     * @param log   slf4j logger
     * @param messageFormat Format for the message that will be displayed.
     */
    MethodLogger(Logger log, String messageFormat) {
        this.log = log;
        this.messageFormat = messageFormat;
    }

    /**
     * Constructor expects a format for how the message should
     * be displayed.  The logger will be obtained when the method is run,
     * as per the no-arg constructor.
     *
     * @param messageFormat Format for the message that will be displayed.
     */
    MethodLogger(String messageFormat) {
        this.messageFormat = messageFormat;
    }

    /**
     * Whenever a test starts, this method logs the name of the test.
     * @param description
     */
    @Override
    protected void starting(Description description) {
        prepareLogger(description);
        log.info(messageFormat, description.getMethodName());
    }

    /**
     * If logger is not initialized, initialize it with the name of the test class.
     * @param description   Description provided by TestWatcher, used to get test class.
     */
    private void prepareLogger(Description description) {
        if (log == null) {
            log = LoggerFactory.getLogger(description.getClassName());
        }
    }
    
}
