package org.service.decoration.inspection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.LeftAngleBracketElement;
import org.service.decoration.elements.NoElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.service.decoration.helpers.ClassContextConverter.State.IN_CLASS;
import static org.service.decoration.inspection.InspectionHelper.classContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/engine-context.xml")
public class LeftAngleBracketInspectionTest {
    @Autowired
    private LeftAngleBracketInspection inspection;

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
