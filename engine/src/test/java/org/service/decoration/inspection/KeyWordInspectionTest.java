package org.service.decoration.inspection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.KeyWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.service.decoration.helpers.ClassContextConverter.State.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/engine-context.xml")
public class KeyWordInspectionTest {
    @Autowired
    private KeyWordInspector inspection;

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
