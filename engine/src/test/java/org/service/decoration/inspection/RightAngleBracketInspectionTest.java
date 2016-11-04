package org.service.decoration.inspection;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.NoElement;
import org.service.decoration.elements.RightAngleBracket;

import static org.service.decoration.helpers.ClassContextConverter.State.IN_CLASS;
import static org.service.decoration.inspection.InspectionHelper.classContext;

public class RightAngleBracketInspectionTest {
    private RightAngleBracketInspection inspection;

    @Before
    public void setUp() {
        inspection = new RightAngleBracketInspection();
    }

    @Test
    public void rightAngleBracketIsReturnedWhenWordIsEqToRightAngleBracket() {
        CodeElement element = inspection.inspect(">", IN_CLASS, classContext(">"));

        Assert.assertEquals(element.getClass(), RightAngleBracket.class);
        Assert.assertEquals(element.getValue(), ">");
    }

    @Test
    public void noElementIsReturnedWhenWordIsIsNotEqToRightAngleBracket() {
        CodeElement element = inspection.inspect("test", IN_CLASS, classContext("test"));

        Assert.assertEquals(element.getClass(), NoElement.class);
    }

}
