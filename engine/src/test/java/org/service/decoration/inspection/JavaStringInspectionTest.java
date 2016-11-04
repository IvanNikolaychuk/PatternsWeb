package org.service.decoration.inspection;

import org.junit.Before;
import org.junit.Test;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.JavaString;

import static org.junit.Assert.assertEquals;
import static org.service.decoration.elements.CodeElement.NO_ELEMENT;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_CLASS;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_COMMENT;

public class JavaStringInspectionTest {
    private JavaStringInspection inspection;

    @Before
    public void setUp() {
        inspection = new JavaStringInspection();
    }

    @Test
    public void nextElementIsJavaStringWhenWeAreInClassState() {
        CodeElement element = inspection.inspect("\"",  IN_CLASS, InspectionHelper.classContext(""));
        assertEquals(element.getClass(), JavaString.class);
        assertEquals(element.getValue(), "\"");

        element = inspection.inspect("string",  IN_CLASS, InspectionHelper.classContext(""));
        assertEquals(element.getClass(), JavaString.class);
        assertEquals(element.getValue(), "string");

        element = inspection.inspect("\"",  IN_CLASS, InspectionHelper.classContext(""));
        assertEquals(element.getClass(), JavaString.class);
        assertEquals(element.getValue(), "\"");

        element = inspection.inspect("test",  IN_CLASS, InspectionHelper.classContext(""));
        assertEquals(element, NO_ELEMENT);
    }

    @Test
    public void nextElementIsNotJavaStringWhenWeAreInCommentState() {
        CodeElement element = inspection
                .inspect("\"", IN_COMMENT, InspectionHelper.classContext(""));
        assertEquals(element, NO_ELEMENT);
    }
}
