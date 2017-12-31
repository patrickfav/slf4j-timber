package at.favre.lib.slf4j;

import timber.log.Timber;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * A mock timber for testing to check the expected properties
 *
 * @author Patrick Favre-Bulle
 */

public class MockTimberTree extends Timber.Tree {

    private boolean isExpectedSet = false;
    private int expectedPrio;
    private String expectedTag;
    private String message;
    private Throwable t;

    MockTimberTree() {

    }

    void setNexExpectedCall(int expectedPrio, String expectedTag, String message, Throwable t) {
        this.expectedPrio = expectedPrio;
        this.expectedTag = expectedTag;
        this.message = message;
        this.t = t;
        isExpectedSet = true;
    }

    @Override
    protected synchronized void log(int priority, String tag, String message, Throwable t) {
        System.out.println("["+toString()+"]"+" prio: " + priority + " - tag: " + tag + " - message: " + message + " - throwable: " + t);

        if (isExpectedSet) {
            assertEquals("log priority should match", expectedPrio, priority);
            assertEquals("log tag should match", expectedTag, tag);
            if(t != null) {
                assertTrue(message.startsWith(this.message));
                assertEquals("log throwable should match", this.t, t);
            } else {
                assertEquals("log message should match", this.message, message);
            }
        }
        isExpectedSet = false;
    }
}
