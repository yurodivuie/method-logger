package com.cegames;

import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for simple MethodLogger.
 */
public class MethodLoggerTest {
    
    private static Logger log = LoggerFactory.getLogger(MethodLoggerTest.class);

    @Rule
    public static MethodLogger methodLogger = new MethodLogger(log);

    @Test
    public void aSpecificTestName() {
        
    }
}
