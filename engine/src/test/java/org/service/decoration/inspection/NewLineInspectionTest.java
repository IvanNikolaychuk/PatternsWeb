package org.service.decoration.inspection;

import org.junit.Before;
import org.junit.Test;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.NewLineElement;
import org.service.decoration.elements.NoElement;

import static org.junit.Assert.assertEquals;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_CLASS;
import static org.service.decoration.inspection.InspectionHelper.classContext;

public class NewLineInspectionTest {
    private NewLineInspection inspection;

    @Before
    public void setUp() {
        inspection = new NewLineInspection();
    }

    @Test
    public void newLineElementIsReturnedWhenWordContainsNewLine() {
        CodeElement element = inspection.inspect("\n", IN_CLASS, classContext("\n"));

        assertEquals(element.getClass(), NewLineElement.class);
        assertEquals(element.getValue(), "\n");
    }

    @Test
    public void noElementIsReturnedWhenWordIsIsNotEqToNewLine() {
        CodeElement element = inspection.inspect("test", IN_CLASS, classContext("test"));

        assertEquals(element.getClass(), NoElement.class);
    }

}
