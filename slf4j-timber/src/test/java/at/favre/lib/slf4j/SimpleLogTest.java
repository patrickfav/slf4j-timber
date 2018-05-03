package at.favre.lib.slf4j;

import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timber.log.Timber;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * @author RISE GmbH (patrick.favre@rise-world.com)
 * @since 31.12.2017
 */

public class SimpleLogTest {
    private static final String TAG = "SimpleLogTest.Log";
    private Logger logger = LoggerFactory.getLogger(TAG);

    private MockTimberTree mockTimberTree;

    @Before
    public void setup() {
        Timber.uprootAll();
        mockTimberTree = new MockTimberTree();
        Timber.plant(mockTimberTree);
    }

    @After
    public void tearDown() {
        Timber.uprootAll();
    }

    @Test
    public void testVerboseLog() {
        logger.trace("a trace message");
        logger.trace("a trace exception", new IllegalStateException());
        logger.trace("a trace {}", "placeholder msg");
        logger.trace("a trace {} {}", "placeholder msg1", "placeholder msg2");
        logger.trace("a trace {} {} {}", "placeholder msg1", "placeholder msg2", "placeholder msg3");

        testLog(Log.VERBOSE, "a trace message test", null);
        testLog(Log.VERBOSE, "a trace message test", new IllegalStateException());
    }

    @Test
    public void testDebugLog() {
        logger.debug("a debug message");
        logger.debug("a debug {}", "placeholder msg");
        logger.debug("a debug {} {}", "placeholder msg1", "placeholder msg2");
        logger.debug("a debug {} {} {}", "placeholder msg1", "placeholder msg2", "placeholder msg3");
        logger.debug("a debug exception", new IllegalStateException());

        testLog(Log.DEBUG, "a debug message test", null);
        testLog(Log.DEBUG, "a debug message test", new IllegalStateException());
    }

    @Test
    public void testInfoLog() {
        logger.info("a info message");
        logger.info("a info {}", "placeholder msg");
        logger.info("a info {} {}", "placeholder msg1", "placeholder msg2");
        logger.info("a info {} {} {}", "placeholder msg1", "placeholder msg2", "placeholder msg3");
        logger.info("a info exception", new IllegalStateException());

        testLog(Log.INFO, "a info message test", null);
        testLog(Log.INFO, "a info message test", new RuntimeException());
    }

    @Test
    public void testWarnLog() {
        logger.warn("a warn message");
        logger.warn("a warn {}", "placeholder msg");
        logger.warn("a warn {} {}", "placeholder msg1", "placeholder msg2");
        logger.warn("a warn {} {} {}", "placeholder msg1", "placeholder msg2", "placeholder msg3");
        logger.warn("a warn exception", new IllegalStateException());

        testLog(Log.WARN, "a warn message test", null);
        testLog(Log.WARN, "a warn message test", new IllegalArgumentException());
    }

    @Test
    public void testErrorLog() {
        logger.error("a error message");
        logger.error("a error {}", "placeholder msg");
        logger.error("a error {} {}", "placeholder msg1", "placeholder msg2");
        logger.error("a error {} {} {}", "placeholder msg1", "placeholder msg2", "placeholder msg3");
        logger.error("a error exception test", new IllegalStateException());

        testLog(Log.ERROR, "a error message test", null);
        testLog(Log.ERROR, "a error message test", new IllegalAccessError());
    }

    @Test
    public void testIsLoggable() {
        assertTrue(logger.isTraceEnabled());
        assertTrue(logger.isDebugEnabled());
        assertTrue(logger.isInfoEnabled());
        assertTrue(logger.isWarnEnabled());
        assertTrue(logger.isErrorEnabled());
    }

    @Test
    public void testIsLoggable_noTreesPlanted() {
        Timber.uprootAll();
        assertFalse(logger.isTraceEnabled());
        assertFalse(logger.isDebugEnabled());
        assertFalse(logger.isInfoEnabled());
        assertFalse(logger.isWarnEnabled());
        assertFalse(logger.isErrorEnabled());
    }

    private void testLog(int priority, String message, Throwable t) {
        mockTimberTree.setNexExpectedCall(priority, TAG, message, t);
        switch (priority) {
            case Log.VERBOSE:
                if (t != null) {
                    logger.trace(message, t);
                } else {
                    logger.trace(message);
                }
                break;
            case Log.DEBUG:
                if (t != null) {
                    logger.debug(message, t);
                } else {
                    logger.debug(message);
                }
                break;
            case Log.INFO:
                if (t != null) {
                    logger.info(message, t);
                } else {
                    logger.info(message);
                }
                break;
            case Log.WARN:
                if (t != null) {
                    logger.warn(message, t);
                } else {
                    logger.warn(message);
                }
                break;
            case Log.ERROR:
                if (t != null) {
                    logger.error(message, t);
                } else {
                    logger.error(message);
                }
                break;
            default:
                throw new IllegalStateException("unknown prio");
        }
    }

}
