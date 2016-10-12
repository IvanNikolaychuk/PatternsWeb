package org.service.decoration.inspection;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.LeftAngleBracketElement;
import org.service.decoration.elements.NewLineElement;
import org.service.decoration.elements.NoElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_CLASS;
import static org.service.decoration.inspection.InspectionHelper.classContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/engine-context.xml")
public class NewLineInspectionTest {
    @Autowired
    private NewLineInspection inspection;

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
