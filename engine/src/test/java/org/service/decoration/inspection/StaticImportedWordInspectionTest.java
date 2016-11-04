package org.service.decoration.inspection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.service.decoration.helpers.ClassContextConverter.State;

import static org.service.decoration.elements.CodeElement.NO_ELEMENT;
import static org.service.decoration.inspection.InspectionHelper.classContext;

public class StaticImportedWordInspectionTest {
    private StaticImportedWordInspection inspection;

    @Before
    public void setUp() {
        inspection = new StaticImportedWordInspection();
    }

    @Test
    public void elementIsStaticImportedWordWhenStateIsNotCommentAndWordContainsOnlyUpperCases() {
        Assert.assertEquals(inspection.inspect("TEST_TEST", State.IN_COMMENT, classContext("")), NO_ELEMENT);
    }

    @Test
    public void elementIsNotStaticImportedWordWhenStateIsNotCommentAndWordContainsNotOnlyUpperCases() {
        Assert.assertEquals(inspection.inspect("TEST_test", State.IN_COMMENT, classContext("")), NO_ELEMENT);
    }

    @Test
    public void elementIsNotStaticImportedWordWhenStateIsComment() {
        Assert.assertEquals(inspection.inspect("TEST", State.IN_COMMENT, classContext("")), NO_ELEMENT);
    }
}
