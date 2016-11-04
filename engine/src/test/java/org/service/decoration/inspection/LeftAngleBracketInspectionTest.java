package org.service.decoration.inspection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.LeftAngleBracketElement;
import org.service.decoration.elements.NoElement;

import static org.service.decoration.helpers.ClassContextConverter.State.IN_CLASS;
import static org.service.decoration.inspection.InspectionHelper.classContext;

public class LeftAngleBracketInspectionTest {
    private LeftAngleBracketInspection inspection;

    @Before
    public void setUp() {
        inspection = new LeftAngleBracketInspection();
    }

    @Test
    public void leftAngleBracketIsReturnedWhenWordIsEqToLeftAngleBracket() {
        CodeElement element = inspection.inspect("<", IN_CLASS, classContext("<"));

        Assert.assertEquals(element.getClass(), LeftAngleBracketElement.class);
        Assert.assertEquals(element.getValue(), "<");
    }

    @Test
    public void noElementIsReturnedWhenWordIsIsNotEqToLeftAngleBracket() {
        CodeElement element = inspection.inspect("test", IN_CLASS, classContext("test"));

        Assert.assertEquals(element.getClass(), NoElement.class);
    }
}
