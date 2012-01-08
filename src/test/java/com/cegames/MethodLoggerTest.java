package com.cegames;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;
import org.mockito.ArgumentCaptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
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
        ArgumentCaptor<ILoggingEvent> argument = ArgumentCaptor.forClass(ILoggingEvent.class);

        try {
            ((ch.qos.logback.classic.Logger) log).addAppender(mockAppender);

            Description description = mock(Description.class);

            when(description.getClassName()).thenReturn(MethodLoggerTest.class.getName());
            when(description.getMethodName()).thenReturn("mockMethod");

            defaultMethodLogger.starting(description);

            verify(mockAppender).doAppend(argument.capture());
            assertThat(argument.getValue().getLoggerName(), equalToIgnoringCase(MethodLoggerTest.class.getName()));
            assertThat(argument.getValue().getMessage(), equalToIgnoringCase(MethodLogger.DEFAULT_FORMAT));
        } finally {
            ((ch.qos.logback.classic.Logger) log).detachAppender(mockAppender);
        }
    }
    
    @Test
    public void testLoggerOnlyConstructor() {
        String methodName = "testMethod";
        Logger mlog = mock(Logger.class);
        Description description = mock(Description.class);
        when(description.getMethodName()).thenReturn(methodName);
        
        MethodLogger testMethodLogger = new MethodLogger(mlog);
        testMethodLogger.starting(description);
        
        verify(mlog).info(MethodLogger.DEFAULT_FORMAT, methodName);
    }

    @Test
    public void testLoggerFormatConstructor() {
        String methodName = "anotherTest";
        String testFormat = "TEST - <{}>!!!!";
        Logger mlog = mock(Logger.class);
        Description description = mock(Description.class);
        when(description.getMethodName()).thenReturn(methodName);

        MethodLogger testMethodLogger = new MethodLogger(mlog, testFormat);
        testMethodLogger.starting(description);

        verify(mlog).info(testFormat, methodName);
    }

    @Test
    public void testFormatOnlyConstructor() {
        String testFormat = "Fancy test started ~@~ +|{}|+";
        String methodName = "mockMethod";
        
        MethodLogger defaultMethodLogger = new MethodLogger(testFormat);

        Appender<ILoggingEvent> mockAppender = mock(Appender.class);
        ArgumentCaptor<ILoggingEvent> argument = ArgumentCaptor.forClass(ILoggingEvent.class);

        try {
            ((ch.qos.logback.classic.Logger) log).addAppender(mockAppender);

            Description description = mock(Description.class);

            when(description.getClassName()).thenReturn(MethodLoggerTest.class.getName());
            when(description.getMethodName()).thenReturn(methodName);

            defaultMethodLogger.starting(description);

            verify(mockAppender).doAppend(argument.capture());

            assertThat(argument.getValue().getLoggerName(), equalToIgnoringCase(MethodLoggerTest.class.getName()));
            assertThat(argument.getValue().getMessage(), equalToIgnoringCase(testFormat));
        } finally {
            ((ch.qos.logback.classic.Logger) log).detachAppender(mockAppender);
        }
    }
}
