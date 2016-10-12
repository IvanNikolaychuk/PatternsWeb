package org.service.decoration.inspection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.Comment;
import org.service.decoration.elements.JavaString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.service.decoration.elements.CodeElement.NO_ELEMENT;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_CLASS;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_COMMENT;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/engine-context.xml")
public class JavaStringInspectionTest {
    @Autowired
    private JavaStringInspection inspection;

    @Test
    public void nextElementIsJavaStringWhenWeAreInClassState() {
        CodeElement element = inspection.inspect("\"someString\"",  IN_CLASS, InspectionHelper.classContext("comment"));

        assertEquals(element.getClass(), JavaString.class);
        assertEquals(element.getValue(), "\"someString\"");
    }

    @Test
    public void nextElementIsNotJavaStringWhenWeAreInCommentState() {
        CodeElement element = inspection
                .inspect("\"something\"", IN_COMMENT, InspectionHelper.classContext("\"something\""));
        assertEquals(element, NO_ELEMENT);
    }
}
