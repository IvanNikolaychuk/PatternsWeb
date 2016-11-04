package org.service.decoration.inspection;

import org.junit.Before;
import org.junit.Test;
import org.service.decoration.context.ClassContext;
import org.service.decoration.elements.ClassField;
import org.service.decoration.elements.CodeElement;

import static org.junit.Assert.assertEquals;
import static org.service.decoration.elements.CodeElement.NO_ELEMENT;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_CLASS;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_COMMENT;


public class ClassFieldInspectionTest {
    private ClassFieldInspection inspection;

    @Before
    public void setUp() {
        inspection = new ClassFieldInspection();
    }

    @Test
    public void noElementIsReturnedWhenCurrentStateIsComment() {
        CodeElement element = inspection.inspect("anything", IN_COMMENT, InspectionHelper.classContext("anything"));
        assertEquals(element, NO_ELEMENT);
    }

    @Test
    public void classVariableIsReturnedWhenWeAreInClassAndDeclareNonInitializedVariable() {
        ClassContext<String> context = InspectionHelper.classContext("hello", ";");
        CodeElement element = inspection.inspect(context.next(), IN_CLASS, context.evaluationCopy());
        assertEquals(element.getClass(), ClassField.class);
        assertEquals(element.getValue(), "hello");
    }

    @Test
    public void classVariableIsReturnedWhenWeAreInClassAndDeclareInitializedVariable() {
        ClassContext<String> context = InspectionHelper.classContext("hello", "=", "12", ";");
        CodeElement element = inspection.inspect(context.next(), IN_CLASS, context.evaluationCopy());
        assertEquals(element.getClass(), ClassField.class);
        assertEquals(element.getValue(), "hello");
    }
}
