package org.service.decoration.inspection;

import org.junit.Before;
import org.junit.Test;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.PunctuationSign;

import static org.junit.Assert.assertEquals;
import static org.service.decoration.helpers.ClassContextConverter.State.*;

public class PunctuationSignInspectionTest {
    private PunctuationSignInspection inspection;

    @Before
    public void setUp() {
        inspection = new PunctuationSignInspection();
    }

    @Test
    public void dotIsReturnedWhenDotExists() {
        CodeElement element = inspection.inspect(".", BEFORE_CLASS, InspectionHelper.classContext("."));
        assertEquals(element.getClass(), PunctuationSign.class);
        assertEquals(element.getValue(), ".");
    }

    @Test
    public void semicolonIsReturnedWhenSemicolonExists() {
        CodeElement element = inspection.inspect(";",  BEFORE_CLASS, InspectionHelper.classContext(";"));
        assertEquals(element.getClass(), PunctuationSign.class);
        assertEquals(element.getValue(), ";");
    }

    @Test
    public void noElementIsReturnedWhenPunctuationSignDoesnotExist() {
        CodeElement element = inspection.inspect("0",  IN_CLASS, InspectionHelper.classContext("0"));
        assertEquals(element, CodeElement.NO_ELEMENT);
    }

    @Test
    public void noElementIsReturnedWhenPunctuationSignIsFoundInComment() {
        CodeElement element = inspection.inspect(";",  IN_COMMENT, InspectionHelper.classContext(";"));
        assertEquals(element, CodeElement.NO_ELEMENT);
    }

}
