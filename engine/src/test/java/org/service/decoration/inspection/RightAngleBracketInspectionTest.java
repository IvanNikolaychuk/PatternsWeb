package org.service.decoration.inspection;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.NoElement;
import org.service.decoration.elements.RightAngleBracket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.service.decoration.helpers.ClassContextConverter.State.IN_CLASS;
import static org.service.decoration.inspection.InspectionHelper.classContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/engine-module-context.xml")
public class RightAngleBracketInspectionTest {
    @Autowired
    private RightAngleBracketInspection inspection;

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
