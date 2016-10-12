package org.service.decoration.inspection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.service.decoration.elements.Annotation;
import org.service.decoration.elements.CodeElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_CLASS;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_COMMENT;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/engine-context.xml")
public class AnnotationInspectionTest {
    @Autowired
    private AnnotationInspection inspection;

    @Test
    public void annotationElementIsReturnedWhenAnnotationExists() {
        CodeElement element = inspection.inspect("@Hello", IN_CLASS, InspectionHelper.classContext("@Hello"));
        assertEquals(element.getClass(), Annotation.class);
        assertEquals(element.getValue(), "@Hello");
    }

    @Test
    public void noElementIsReturnedWhenAnnotationDoesnotExist() {
        CodeElement element = inspection.inspect("0", IN_CLASS, InspectionHelper.classContext("0"));
        assertEquals(element, CodeElement.NO_ELEMENT);
    }

    @Test
    public void noElementIsReturnedWhenAnnotationIsFoundOutOfClass() {
        CodeElement element = inspection.inspect("@Hello", IN_COMMENT, InspectionHelper.classContext("@Hello"));
        assertEquals(element, CodeElement.NO_ELEMENT);
    }
}
