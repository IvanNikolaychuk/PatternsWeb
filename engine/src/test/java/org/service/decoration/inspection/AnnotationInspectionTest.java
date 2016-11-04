package org.service.decoration.inspection;

import org.junit.Before;
import org.junit.Test;
import org.service.decoration.elements.Annotation;
import org.service.decoration.elements.CodeElement;

import static org.junit.Assert.assertEquals;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_CLASS;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_COMMENT;

public class AnnotationInspectionTest {
    private AnnotationInspection inspection;

    @Before
    public void setUp() {
        inspection = new AnnotationInspection();
    }

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
