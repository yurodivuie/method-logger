package com.cegames;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for simple MethodLogger.
 */
public class MethodLoggerTest {

    private static Logger log = LoggerFactory.getLogger(MethodLoggerTest.class);

    /**
     * This is just to demonstrate to a user how it works.
     */
    @Rule
    public static MethodLogger methodLogger = new MethodLogger();

    @Test
    public void testEmptyConstructor() {
        MethodLogger defaultMethodLogger = new MethodLogger();

        Appender<ILoggingEvent> mockAppender = mock(Appender.class);

        try {
            ((ch.qos.logback.classic.Logger) log).addAppender(mockAppender);

            Description description = mock(Description.class);

            when(description.getClassName()).thenReturn(MethodLoggerTest.class.getName());
            when(description.getMethodName()).thenReturn("mockMethod");

            defaultMethodLogger.starting(description);

            verify(mockAppender).doAppend(any(ILoggingEvent.class));
        } finally {
            ((ch.qos.logback.classic.Logger) log).detachAppender(mockAppender);
        }
    }
    
    @Test
    public void testLoggerConstructor() {
        String methodName = "testMethod";
        Logger mlog = mock(Logger.class);
        Description description = mock(Description.class);
        when(description.getMethodName()).thenReturn(methodName);
        
        MethodLogger testMethodLogger = new MethodLogger(mlog);
        testMethodLogger.starting(description);
        
        verify(mlog).info(MethodLogger.DEFAULT_FORMAT, methodName);
    }

    @Test
    public void testLoggerPrefixConstructor() {
        String methodName = "anotherTest";
        String testFormat = "<{}> - <{}>!!!!";
        Logger mlog = mock(Logger.class);
        Description description = mock(Description.class);
        when(description.getMethodName()).thenReturn(methodName);

        MethodLogger testMethodLogger = new MethodLogger(mlog, testFormat);
        testMethodLogger.starting(description);

        verify(mlog).info(testFormat, methodName);
    }
}
