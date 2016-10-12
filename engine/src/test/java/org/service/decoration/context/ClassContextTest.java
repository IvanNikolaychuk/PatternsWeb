package org.service.decoration.context;

import org.junit.Test;
import org.service.decoration.context.ClassContext;

import static org.junit.Assert.*;


public class ClassContextTest {

    @Test
    public void iteratingThroughContextCopyHasNoInfluenceToOriginContext() {
        ClassContext<String> originalContext = new ClassContext<>(new String[]{"first", "last"});

        assertEquals(originalContext.next(), "first");

        ClassContext<String> copy = originalContext.evaluationCopy();
        assertEquals(copy.next(), "last");

        assertEquals(originalContext.next(), "last");
    }

    @Test
    public void ableToIterateViaContextWithoutGoingOunOfBorders() {
        ClassContext context = new ClassContext<>(new String[]{"first", "middle", "last"});

        assertTrue(context.hasNext());
        assertEquals(context.next(), "first");
        assertTrue(context.hasNext());
        assertEquals(context.next(), "middle");
        assertTrue(context.hasNext());
        assertEquals(context.next(), "last");
        assertFalse(context.hasNext());
    }

    @Test
    public void upperBorderIsViolated() {
        ClassContext context = new ClassContext<>(new String[]{"last"});

        assertEquals(context.next(), "last");
        assertFalse(context.hasNext());

        assertEquals(context.next(), null);
        assertEquals(context.next(), null);
    }
}
