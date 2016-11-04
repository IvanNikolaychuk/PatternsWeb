package org.service.decoration.inspection;

import org.junit.Before;
import org.junit.Test;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.KeyWord;

import static org.junit.Assert.assertEquals;
import static org.service.decoration.helpers.ClassContextConverter.State.*;

public class KeyWordInspectionTest {
    private KeyWordInspector inspection;

    @Before
    public void setUp() {
        inspection = new KeyWordInspector();
    }

    @Test
    public void keyWordIsReturnedWhenKeyWordExists() {
        CodeElement element = inspection.inspect("private", IN_METHOD, InspectionHelper.classContext("private"));
        assertEquals(element.getClass(), KeyWord.class);
        assertEquals(element.getValue(), "private");
    }

    @Test
    public void noElementIsReturnedWhenKeywordDoesnotExist() {
        CodeElement element = inspection.inspect("0", IN_CLASS, InspectionHelper.classContext("private"));
        assertEquals(element, CodeElement.NO_ELEMENT);
    }

    @Test
    public void noElementIsReturnedWhenKeyWordIsFoundInComment() {
        CodeElement element = inspection.inspect("private", IN_COMMENT, InspectionHelper.classContext("private"));
        assertEquals(element, CodeElement.NO_ELEMENT);
    }
}
