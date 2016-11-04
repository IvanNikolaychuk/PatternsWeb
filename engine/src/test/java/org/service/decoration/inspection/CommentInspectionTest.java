package org.service.decoration.inspection;

import org.junit.Before;
import org.junit.Test;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.Comment;

import static org.junit.Assert.assertEquals;
import static org.service.decoration.elements.CodeElement.NO_ELEMENT;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_CLASS;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_COMMENT;



public class CommentInspectionTest {
    private CommentInspection inspection;

    @Before
    public void setUp() {
        inspection = new CommentInspection();
    }

    @Test
    public void nextElementIsCommentWhenWeAreInCommentState() {
        CodeElement element = inspection.inspect("comment",  IN_COMMENT, InspectionHelper.classContext("comment"));

        assertEquals(element.getClass(), Comment.class);
        assertEquals(element.getValue(), "comment");
    }

    @Test
    public void nextElementInNotCommentWhenWeAreInNonCommentStateAndElementIsNotNewLine() {
        CodeElement element = inspection.inspect("something", IN_CLASS, InspectionHelper.classContext("something"));
        assertEquals(element, NO_ELEMENT);
    }
}
