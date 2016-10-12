package org.service.decoration.inspection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.service.decoration.context.ClassContext;
import org.service.decoration.elements.ClassField;
import org.service.decoration.elements.CodeElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_CLASS;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_COMMENT;
import static org.service.decoration.elements.CodeElement.NO_ELEMENT;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/engine-context.xml")
public class ClassFieldInspectionTest {
        @Autowired
        private ClassFieldInspection inspection;

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
