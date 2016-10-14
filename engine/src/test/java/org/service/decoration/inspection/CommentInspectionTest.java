package org.service.decoration.inspection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.service.decoration.elements.CodeElement;
import org.service.decoration.elements.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_CLASS;
import static org.service.decoration.helpers.ClassContextConverter.State.IN_COMMENT;
import static org.service.decoration.elements.CodeElement.NO_ELEMENT;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/engine-module-context.xml")
public class CommentInspectionTest {

    @Autowired
    private CommentInspection inspection;

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
